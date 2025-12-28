# Write your MySQL query statement below
WITH ordered_sessions AS (
    SELECT
        ss.*,
        LAG(session_date) OVER (
            PARTITION BY student_id ORDER BY session_date
        ) AS prev_date
    FROM study_sessions ss
),

session_groups AS (
    SELECT *,
        SUM(
            CASE
                WHEN prev_date IS NULL
                     OR DATEDIFF(session_date, prev_date) > 2
                THEN 1 ELSE 0
            END
        ) OVER (
            PARTITION BY student_id ORDER BY session_date
        ) AS grp
    FROM ordered_sessions
),

numbered_sessions AS (
    SELECT
        student_id,
        grp,
        subject,
        session_date,
        hours_studied,
        ROW_NUMBER() OVER (
            PARTITION BY student_id, grp ORDER BY session_date
        ) AS rn
    FROM session_groups
),

cycle_detection AS (
    SELECT
        student_id,
        grp,
        COUNT(*) AS total_sessions,
        COUNT(DISTINCT subject) AS cycle_length,
        SUM(hours_studied) AS total_study_hours
    FROM numbered_sessions
    GROUP BY student_id, grp
    HAVING
        COUNT(*) >= 6
        AND COUNT(DISTINCT subject) >= 3
        AND COUNT(*) >= 2 * COUNT(DISTINCT subject)
)

SELECT
    s.student_id,
    s.student_name,
    s.major,
    cd.cycle_length,
    ROUND(cd.total_study_hours, 1) AS total_study_hours
FROM cycle_detection cd
JOIN students s
    ON s.student_id = cd.student_id
ORDER BY
    cd.cycle_length DESC,
    total_study_hours DESC;

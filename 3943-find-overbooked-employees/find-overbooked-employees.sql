# Write your MySQL query statement below
WITH weekly_meetings AS (
    SELECT
        employee_id,
        YEARWEEK(meeting_date, 1) AS year_week,
        SUM(duration_hours) AS total_weekly_hours
    FROM meetings
    GROUP BY employee_id, YEARWEEK(meeting_date, 1)
),
meeting_heavy AS (
    SELECT
        employee_id,
        COUNT(*) AS meeting_heavy_weeks
    FROM weekly_meetings
    WHERE total_weekly_hours > 20
    GROUP BY employee_id
)
SELECT
    e.employee_id,
    e.employee_name,
    e.department,
    m.meeting_heavy_weeks
FROM meeting_heavy m
JOIN employees e
  ON m.employee_id = e.employee_id
WHERE m.meeting_heavy_weeks >= 2
ORDER BY
    m.meeting_heavy_weeks DESC,
    e.employee_name ASC;

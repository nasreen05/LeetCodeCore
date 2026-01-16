WITH efficiency_by_half AS (
    SELECT
        t.driver_id,
        CASE
            WHEN MONTH(t.trip_date) BETWEEN 1 AND 6 THEN 'first'
            ELSE 'second'
        END AS half,
        AVG(t.distance_km / t.fuel_consumed) AS avg_efficiency
    FROM trips t
    GROUP BY t.driver_id, half
),
pivoted AS (
    SELECT
        driver_id,
        MAX(CASE WHEN half = 'first' THEN avg_efficiency END) AS first_half_avg,
        MAX(CASE WHEN half = 'second' THEN avg_efficiency END) AS second_half_avg
    FROM efficiency_by_half
    GROUP BY driver_id
)
SELECT
    d.driver_id,
    d.driver_name,
    ROUND(p.first_half_avg, 2) AS first_half_avg,
    ROUND(p.second_half_avg, 2) AS second_half_avg,
    ROUND(p.second_half_avg - p.first_half_avg, 2) AS efficiency_improvement
FROM pivoted p
JOIN drivers d ON d.driver_id = p.driver_id
WHERE p.first_half_avg IS NOT NULL
  AND p.second_half_avg IS NOT NULL
  AND p.second_half_avg > p.first_half_avg
ORDER BY 
    (p.second_half_avg - p.first_half_avg) DESC,
    d.driver_id DESC;

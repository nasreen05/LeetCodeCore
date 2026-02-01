# Write your MySQL query statement below
WITH process AS (
  SELECT
    store_id,
    FIRST_VALUE(product_name) OVER (PARTITION BY store_id ORDER BY price DESC) AS most_exp_product,
    FIRST_VALUE(product_name) OVER (PARTITION BY store_id ORDER BY price) AS cheapest_product,
    FIRST_VALUE(quantity) OVER (PARTITION BY store_id ORDER BY price DESC) AS most_expensive_quantity,
    FIRST_VALUE(quantity) OVER (PARTITION BY store_id ORDER BY price) AS cheapest_quantity,
    ROW_NUMBER() OVER (PARTITION BY store_id ORDER BY store_id) AS rn,
    COUNT(*) OVER (PARTITION BY store_id) AS cnt
  FROM inventory
)
SELECT
  store_id, store_name, location, most_exp_product, cheapest_product,
  ROUND(cheapest_quantity / most_expensive_quantity, 2) AS imbalance_ratio
FROM process
JOIN stores USING (store_id)
WHERE rn = 1 AND cnt >= 3
  AND (cheapest_quantity / most_expensive_quantity) > 1
ORDER BY imbalance_ratio DESC, store_name;
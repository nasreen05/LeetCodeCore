# Write your MySQL query statement below
# Write your MySQL query statement below
SELECT 
    tt.user_id as user_id,
    (
        SELECT r.reaction
        FROM reactions r
        WHERE r.user_id = tt.user_id
        GROUP BY r.reaction
        ORDER BY COUNT(*) DESC
        LIMIT 1
    ) AS dominant_reaction,
    ROUND(tt.reaction_ratio / 100, 2) as reaction_ratio
FROM (
    SELECT 
    t.user_id as user_id,
    t.reaction as dominant_reaction,
    (MAX(cR) / SUM(t.cR)) * 100 as reaction_ratio
FROM (
    SELECT 
    user_id,
    COUNT(reaction) as cR,
    reaction
FROM reactions
GROUP BY user_id, reaction
) as t
GROUP BY user_id
HAVING SUM(cR) >= 5 
) as tt
WHERE tt.reaction_ratio >= 60 
ORDER BY tt.reaction_ratio DESC, tt.user_id ASC
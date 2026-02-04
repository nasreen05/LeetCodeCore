# Write your MySQL query statement below
with a as(select user_id
from course_completions
group by user_id
having count(distinct course_id) >= 5
and avg(course_rating) >= 4),
s as(select course_name, lag(course_name) over(partition by c.user_id order by completion_date desc) x
from a
inner join course_completions c
on a.user_id = c.user_id)
select course_name first_course, x second_course, count(1) transition_count
from s
where x is not null
group by course_name, x
order by transition_count desc, first_course, second_course;
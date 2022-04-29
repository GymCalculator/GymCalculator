INSERT INTO users (username,email,password,role,bodyweight,is_account_non_expired,is_account_non_locked,is_credentials_non_expired,is_enabled,sex,units,user_age)
SELECT * FROM (SELECT 'admin' as username,'admin@gmail.com' as email,
                      '$2a$10$sfdiMp2oK5gNVDl2j2dbK.SlwXjdoaYVUyo2F2cnqdRp..DKY8L8q' as password,
                      2 as role,0 as bodyweight,true as is_account_non_expired,true as is_account_non_locked,
                      true as is_credentials_non_expired,true as is_enabled,0 as sex,0 as units
                      ,NOW() as user_age) as val
WHERE NOT EXISTS(
        select username from users where username = 'admin'
    ) LIMIT 1;
INSERT INTO category
SELECT * FROM (SELECT 'Squat' as category_name) as val
WHERE NOT EXISTS (
        select category_name from category where category_name = 'Squat'
    ) LIMIT 1;
INSERT INTO category
SELECT * FROM (SELECT 'Floor Pull' as category_name) as val
WHERE NOT EXISTS (
        select category_name from category where category_name = 'Floor Pull'
    ) LIMIT 1;
INSERT INTO category
SELECT * FROM (SELECT 'Horizontal Press' as category_name) as val
WHERE NOT EXISTS (
        select category_name from category where category_name = 'Horizontal Press'
    ) LIMIT 1;
INSERT INTO category
SELECT * FROM (SELECT 'Vertical Press' as category_name) as val
WHERE NOT EXISTS (
        select category_name from category where category_name = 'Vertical Press'
    ) LIMIT 1;
INSERT INTO category
SELECT * FROM (SELECT 'Pull-up / Row' as category_name) as val
WHERE NOT EXISTS (
        select category_name from category where category_name = 'Pull-up / Row'
    ) LIMIT 1;
INSERT INTO exercise (exercise_name, reps ,type, weight, checked, category_name)
SELECT * FROM (SELECT 'Back Squat' as exercise_name,0 as reps, 0 as type, 0 as weight, false as checked, 'Squat' as category_name) as val
WHERE NOT EXISTS (
        select exercise_name from exercise where exercise_name = 'Back Squat'
    ) LIMIT 1;
INSERT INTO exercise (exercise_name, reps ,type, weight, checked, category_name)
SELECT * FROM (SELECT 'Front Squat' as exercise_name,0 as reps, 0 as type, 0 as weight, false as checked, 'Squat' as category_name) as val
WHERE NOT EXISTS (
        select exercise_name from exercise where exercise_name = 'Front Squat'
    ) LIMIT 1;
INSERT INTO exercise (exercise_name, reps ,type, weight, checked, category_name)
SELECT * FROM (SELECT 'Deadlift' as exercise_name,0 as reps, 0 as type, 0 as weight, false as checked, 'Floor Pull' as category_name) as val
WHERE NOT EXISTS (
        select exercise_name from exercise where exercise_name = 'Deadlift'
    ) LIMIT 1;
INSERT INTO exercise (exercise_name, reps ,type, weight, checked, category_name)
SELECT * FROM (SELECT 'Sumo Deadlift' as exercise_name,0 as reps, 0 as type, 0 as weight, false as checked, 'Floor Pull' as category_name) as val
WHERE NOT EXISTS (
        select exercise_name from exercise where exercise_name = 'Sumo Deadlift'
    ) LIMIT 1;
INSERT INTO exercise (exercise_name, reps ,type, weight, checked, category_name)
SELECT * FROM (SELECT 'Power Clean' as exercise_name,0 as reps, 0 as type, 0 as weight, false as checked, 'Floor Pull' as category_name) as val
WHERE NOT EXISTS (
        select exercise_name from exercise where exercise_name = 'Power Clean'
    ) LIMIT 1;
INSERT INTO exercise (exercise_name, reps ,type, weight, checked, category_name)
SELECT * FROM (SELECT 'Bench Press' as exercise_name,0 as reps, 0 as type, 0 as weight, false as checked, 'Horizontal Press' as category_name) as val
WHERE NOT EXISTS (
        select exercise_name from exercise where exercise_name = 'Bench Press'
    ) LIMIT 1;
INSERT INTO exercise (exercise_name, reps ,type, weight, checked, category_name)
SELECT * FROM (SELECT 'Incline Bench Press' as exercise_name,0 as reps, 0 as type, 0 as weight, false as checked, 'Horizontal Press' as category_name) as val
WHERE NOT EXISTS (
        select exercise_name from exercise where exercise_name = 'Incline Bench Press'
    ) LIMIT 1;
INSERT INTO exercise (exercise_name, reps ,type, weight, checked, category_name)
SELECT * FROM (SELECT 'Dip' as exercise_name,0 as reps, 1 as type, 0 as weight, false as checked, 'Horizontal Press' as category_name) as val
WHERE NOT EXISTS (
        select exercise_name from exercise where exercise_name = 'Dip'
    ) LIMIT 1;
INSERT INTO exercise (exercise_name, reps ,type, weight, checked, category_name)
SELECT * FROM (SELECT 'Overhead Press' as exercise_name,0 as reps, 0 as type, 0 as weight, false as checked, 'Vertical Press' as category_name) as val
WHERE NOT EXISTS (
        select exercise_name from exercise where exercise_name = 'Overhead Press'
    ) LIMIT 1;
INSERT INTO exercise (exercise_name, reps ,type, weight, checked, category_name)
SELECT * FROM (SELECT 'Push Press' as exercise_name,0 as reps, 0 as type, 0 as weight, false as checked, 'Vertical Press' as category_name) as val
WHERE NOT EXISTS (
        select exercise_name from exercise where exercise_name = 'Push Press'
    ) LIMIT 1;
INSERT INTO exercise (exercise_name, reps ,type, weight, checked, category_name)
SELECT * FROM (SELECT 'Snatch Press' as exercise_name,0 as reps, 0 as type, 0 as weight, false as checked, 'Vertical Press' as category_name) as val
WHERE NOT EXISTS (
        select exercise_name from exercise where exercise_name = 'Snatch Press'
    ) LIMIT 1;
INSERT INTO exercise (exercise_name, reps ,type, weight, checked, category_name)
SELECT * FROM (SELECT 'Chin-up' as exercise_name,0 as reps, 1 as type, 0 as weight, false as checked, 'Pull-up / Row' as category_name) as val
WHERE NOT EXISTS (
        select exercise_name from exercise where exercise_name = 'Chin-up'
    ) LIMIT 1;
INSERT INTO exercise (exercise_name, reps ,type, weight, checked, category_name)
SELECT * FROM (SELECT 'Pull-up' as exercise_name,0 as reps, 1 as type, 0 as weight, false as checked, 'Pull-up / Row' as category_name) as val
WHERE NOT EXISTS (
        select exercise_name from exercise where exercise_name = 'Pull-up'
    ) LIMIT 1;
INSERT INTO exercise (exercise_name, reps ,type, weight, checked, category_name)
SELECT * FROM (SELECT 'Pendlay Row' as exercise_name,0 as reps, 0 as type, 0 as weight, false as checked, 'Pull-up / Row' as category_name) as val
WHERE NOT EXISTS(
    select exercise_name from exercise where exercise_name = 'Pendlay Row'
    ) LIMIT 1;
SELECT *
FROM (SELECT 'Squat' as category_name) as val
WHERE NOT EXISTS(
        select category_name from category where category_name = 'Squat'
    ) LIMIT 1;
INSERT INTO category
SELECT *
FROM (SELECT 'Floor Pull' as category_name) as val
WHERE NOT EXISTS(
        select category_name from category where category_name = 'Floor Pull'
    ) LIMIT 1;
INSERT INTO category
SELECT *
FROM (SELECT 'Horizontal Press' as category_name) as val
WHERE NOT EXISTS(
        select category_name from category where category_name = 'Horizontal Press'
    ) LIMIT 1;
INSERT INTO category
SELECT *
FROM (SELECT 'Vertical Press' as category_name) as val
WHERE NOT EXISTS(
        select category_name from category where category_name = 'Vertical Press'
    ) LIMIT 1;
INSERT INTO category
SELECT *
FROM (SELECT 'Pull-up / Row' as category_name) as val
WHERE NOT EXISTS(
        select category_name from category where category_name = 'Pull-up / Row'
    ) LIMIT 1;

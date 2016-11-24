INSERT INTO students (name, grade, identification_number, username, password)
  VALUES ('Adrian', 9.5, '1921204272770', 'adi', '123');

INSERT INTO admins (name, username, password)
  VALUES ('Admin', 'admin', '123');

INSERT INTO schools (name)
VALUES ('Scoala Nr. 1');

INSERT INTO school_preferences (max_students_allowed, min_grade_allowed, school_id)
VALUES (20, 80, 1);

INSERT INTO schools (name)
VALUES ('Scoala Nr. 2');

INSERT INTO school_preferences (max_students_allowed, min_grade_allowed, school_id)
VALUES (100, 5, 2);

INSERT INTO schools (name)
VALUES ('Scoala Nr. 3');

INSERT INTO school_preferences (max_students_allowed, min_grade_allowed, school_id)
VALUES (50, 2, 3);
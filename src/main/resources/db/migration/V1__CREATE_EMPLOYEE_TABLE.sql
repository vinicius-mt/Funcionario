CREATE TABLE employee (
    id INT4 GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    nis_number VARCHAR(11) NOT NULL
);

COMMENT ON TABLE employee IS 'Employees table';
COMMENT ON COLUMN employee.id IS 'Employee id';
COMMENT ON COLUMN employee.name IS 'Employee name';
COMMENT ON COLUMN employee.last_name IS 'Employee last name';
COMMENT ON COLUMN employee.email IS 'Employee email';
COMMENT ON COLUMN employee.nis_number IS 'Employee PIS number';
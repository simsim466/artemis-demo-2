DELETE FROM reports;

INSERT INTO reports (ID, TEXT, DATE, QUEUE)
VALUES
(4, 'привет', '2021-05-01 21:00:00', 'myfirstqueue'),
(22, 'как дела', '2021-05-01 21:3:00', 'myfirstqueue'),
(222, 'поздравляю', '2021-05-02 0:00:00', 'mysecondqueue'),
(12, 'давай', '2021-05-02 0:00:00', 'mysecondqueue'),
(78, 'до свидания', '2021-05-03 0:00:00', 'mythirdqueue'),
(999, 'чудо', '2021-05-03 0:00:00', 'mythirdqueue'),
(234, 'пока', '2021-05-03 0:00:00', 'mythirdqueue'),
(95, 'чао', '2021-05-25 23:11:5', 'myfirstqueue');
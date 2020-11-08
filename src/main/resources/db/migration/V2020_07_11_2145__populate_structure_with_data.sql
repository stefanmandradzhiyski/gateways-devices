INSERT INTO gateway
(id, serial_number, name, ip_address)
VALUES
(1, "56234971", "Broadcom", "192.168.1.1"),
(2, "87926321", "Qualcomm", "192.168.0.1"),
(3, "11859076", "Cisco", "192.168.1.254");

INSERT INTO device
(id, vendor, date_created, status, gateway_id)
VALUES
(1, "Amazon", "2019-11-10", "ONLINE", 3),
(2, "Cisco", "2018-10-14", "OFFLINE", 2),
(3, "Broadcom", "2017-09-15", "ONLINE", 2),
(4, "TP-LINK", "2018-06-09", "OFFLINE", 2),
(5, "Cisco", "2019-12-25", "ONLINE", 2),
(6, "TP-LINK", "2018-05-24", "OFFLINE", 2),
(7, "Broadcom", "2016-07-29", "ONLINE", 1),
(8, "Amazon", "2015-11-22", "OFFLINE", NULL),
(9, "Cisco", "2018-04-04", "ONLINE", 1),
(10, "Broadcom", "2019-03-09", "OFFLINE", 3),
(11, "TP-LINK", "2018-01-05", "ONLINE", 2),
(12, "Cisco", "2020-05-06", "ONLINE", 2),
(13, "Broadcom", "2018-08-14", "OFFLINE", NULL),
(14, "Cisco", "2020-10-26", "ONLINE", 2),
(15, "TP-LINK", "2016-02-13", "OFFLINE", 2);
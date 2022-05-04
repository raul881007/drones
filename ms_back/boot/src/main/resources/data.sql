

--
-- TOC entry 3272 (class 0 OID 68743)
-- Dependencies: 201
-- Data for Name: drone; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.drone (id, model, serial_number, state, weight, battery) VALUES (6, 'Lightweight', 'first drone', 'IDLE', 92, 40);
INSERT INTO public.drone (id, model, serial_number, state, weight, battery) VALUES (8, 'Lightweight', 'second drone', 'IDLE', 92, 40);
INSERT INTO public.drone (id, model, serial_number, state, weight, battery) VALUES (10, 'Lightweight', 'second drone', 'IDLE', 92, 40);
INSERT INTO public.drone (id, model, serial_number, state, weight, battery) VALUES (9, 'Lightweight', 'second drone', 'IDLE', 92, 15);
INSERT INTO public.drone (id, model, serial_number, state, weight, battery) VALUES (11, 'Lightweight', 'second drone', 'IDLE', 92, 20);
INSERT INTO public.drone (id, model, serial_number, state, weight, battery) VALUES (12, 'Lightweight', 'other drone', 'IDLE', 500, 90);
INSERT INTO public.drone (id, model, serial_number, state, weight, battery) VALUES (13, 'Lightweight', 'my drone', 'IDLE', 68, 40);
INSERT INTO public.drone (id, model, serial_number, state, weight, battery) VALUES (14, 'Lightweight', 'my drone', 'IDLE', 68, 40);
INSERT INTO public.drone (id, model, serial_number, state, weight, battery) VALUES (15, 'Lightweight', 'my drone', 'IDLE', 68, 40);


--
-- TOC entry 3274 (class 0 OID 68755)
-- Dependencies: 203
-- Data for Name: load; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.load (id, code, image, name, weight, drone_id) VALUES (13, 'THISISTHECODE', 'imagePath', 'nameLoad', 12, NULL);
INSERT INTO public.load (id, code, image, name, weight, drone_id) VALUES (14, 'THISISTHECODE', 'imagePath', 'nameLoad', 12, 10);
INSERT INTO public.load (id, code, image, name, weight, drone_id) VALUES (15, 'THISISTHECODE', 'imagePath', 'secondLoad', 62, 10);
INSERT INTO public.load (id, code, image, name, weight, drone_id) VALUES (16, 'THISISTHECODE', 'imagePath', 'thirdLoad', 62, NULL);
INSERT INTO public.load (id, code, image, name, weight, drone_id) VALUES (17, 'THISISTHECODE', 'imagePath', 'thirdLoad', 12, 6);
INSERT INTO public.load (id, code, image, name, weight, drone_id) VALUES (18, 'THISISTHECODE', 'imagePath', 'lastLoad', 12, NULL);


--
-- TOC entry 3284 (class 0 OID 0)
-- Dependencies: 204
-- Name: battery_history_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.battery_history_id_seq', 1, true);


--
-- TOC entry 3285 (class 0 OID 0)
-- Dependencies: 200
-- Name: drone_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.drone_id_seq', 15, true);


--
-- TOC entry 3286 (class 0 OID 0)
-- Dependencies: 202
-- Name: load_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.load_id_seq', 18, true);


-- Completed on 2022-05-04 16:51:21 CDT

--
-- PostgreSQL database dump complete
--


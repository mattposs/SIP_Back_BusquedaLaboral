-- ==========================================
-- 1. TABLAS INDEPENDIENTES (Sin Claves Foráneas)
-- ==========================================

-- Habilidades (Skills) - Extraídas del SkillCatalogSeeder.java
INSERT INTO skill (id, name, type)
VALUES
-- Habilidades Técnicas (Tech Skills)
('20000000-0000-0000-0000-000000000001', 'Java', 'TECNICA'),
('20000000-0000-0000-0000-000000000002', 'Spring Boot', 'TECNICA'),
('20000000-0000-0000-0000-000000000003', 'Kotlin', 'TECNICA'),
('20000000-0000-0000-0000-000000000004', 'Python', 'TECNICA'),
('20000000-0000-0000-0000-000000000005', 'Django', 'TECNICA'),
('20000000-0000-0000-0000-000000000006', 'FastAPI', 'TECNICA'),
('20000000-0000-0000-0000-000000000007', 'Node.js', 'TECNICA'),
('20000000-0000-0000-0000-000000000008', 'TypeScript', 'TECNICA'),
('20000000-0000-0000-0000-000000000009', 'JavaScript', 'TECNICA'),
('20000000-0000-0000-0000-000000000010', 'React', 'TECNICA'),
('20000000-0000-0000-0000-000000000011', 'Next.js', 'TECNICA'),
('20000000-0000-0000-0000-000000000012', 'Angular', 'TECNICA'),
('20000000-0000-0000-0000-000000000013', 'Vue.js', 'TECNICA'),
('20000000-0000-0000-0000-000000000014', 'HTML', 'TECNICA'),
('20000000-0000-0000-0000-000000000015', 'CSS', 'TECNICA'),
('20000000-0000-0000-0000-000000000016', 'Tailwind CSS', 'TECNICA'),
('20000000-0000-0000-0000-000000000017', 'Go', 'TECNICA'),
('20000000-0000-0000-0000-000000000018', 'Rust', 'TECNICA'),
('20000000-0000-0000-0000-000000000019', 'C Sharp', 'TECNICA'),
('20000000-0000-0000-0000-000000000020', 'DotNet', 'TECNICA'),
('20000000-0000-0000-0000-000000000021', 'PHP', 'TECNICA'),
('20000000-0000-0000-0000-000000000022', 'Ruby', 'TECNICA'),
('20000000-0000-0000-0000-000000000023', 'Ruby on Rails', 'TECNICA'),
('20000000-0000-0000-0000-000000000024', 'Swift', 'TECNICA'),
('20000000-0000-0000-0000-000000000025', 'Desarrollo iOS', 'TECNICA'),
('20000000-0000-0000-0000-000000000026', 'Desarrollo Android', 'TECNICA'),
('20000000-0000-0000-0000-000000000027', 'Flutter', 'TECNICA'),
('20000000-0000-0000-0000-000000000028', 'React Native', 'TECNICA'),
('20000000-0000-0000-0000-000000000029', 'SQL', 'TECNICA'),
('20000000-0000-0000-0000-000000000030', 'PostgreSQL', 'TECNICA'),
('20000000-0000-0000-0000-000000000031', 'MySQL', 'TECNICA'),
('20000000-0000-0000-0000-000000000032', 'MongoDB', 'TECNICA'),
('20000000-0000-0000-0000-000000000033', 'Redis', 'TECNICA'),
('20000000-0000-0000-0000-000000000034', 'Elasticsearch', 'TECNICA'),
('20000000-0000-0000-0000-000000000035', 'GraphQL', 'TECNICA'),
('20000000-0000-0000-0000-000000000036', 'REST APIs', 'TECNICA'),
('20000000-0000-0000-0000-000000000037', 'Microservicios', 'TECNICA'),
('20000000-0000-0000-0000-000000000038', 'Docker', 'TECNICA'),
('20000000-0000-0000-0000-000000000039', 'Kubernetes', 'TECNICA'),
('20000000-0000-0000-0000-000000000040', 'AWS', 'TECNICA'),
('20000000-0000-0000-0000-000000000041', 'Azure', 'TECNICA'),
('20000000-0000-0000-0000-000000000042', 'Google Cloud', 'TECNICA'),
('20000000-0000-0000-0000-000000000043', 'Terraform', 'TECNICA'),
('20000000-0000-0000-0000-000000000044', 'CI/CD', 'TECNICA'),
('20000000-0000-0000-0000-000000000045', 'Git', 'TECNICA'),
('20000000-0000-0000-0000-000000000046', 'Linux', 'TECNICA'),
('20000000-0000-0000-0000-000000000047', 'Apache Kafka', 'TECNICA'),
('20000000-0000-0000-0000-000000000048', 'RabbitMQ', 'TECNICA'),
('20000000-0000-0000-0000-000000000049', 'Diseño de Sistemas', 'TECNICA'),
('20000000-0000-0000-0000-000000000050', 'Ingeniería de Datos', 'TECNICA'),
('20000000-0000-0000-0000-000000000051', 'Apache Spark', 'TECNICA'),
('20000000-0000-0000-0000-000000000052', 'Machine Learning', 'TECNICA'),
('20000000-0000-0000-0000-000000000053', 'TensorFlow', 'TECNICA'),
('20000000-0000-0000-0000-000000000054', 'PyTorch', 'TECNICA'),
('20000000-0000-0000-0000-000000000055', 'Análisis de Datos', 'TECNICA'),
('20000000-0000-0000-0000-000000000056', 'Power BI', 'TECNICA'),
('20000000-0000-0000-0000-000000000057', 'Tableau', 'TECNICA'),
('20000000-0000-0000-0000-000000000058', 'Figma', 'TECNICA'),
('20000000-0000-0000-0000-000000000059', 'Diseño UI', 'TECNICA'),
('20000000-0000-0000-0000-000000000060', 'Investigación UX', 'TECNICA'),
('20000000-0000-0000-0000-000000000061', 'Selenium', 'TECNICA'),
('20000000-0000-0000-0000-000000000062', 'Automatización QA', 'TECNICA'),
('20000000-0000-0000-0000-000000000063', 'Ciberseguridad', 'TECNICA'),
('20000000-0000-0000-0000-000000000064', 'DevOps', 'TECNICA'),
('20000000-0000-0000-0000-000000000065', 'Scrum', 'TECNICA'),
('20000000-0000-0000-0000-000000000066', 'Jira', 'TECNICA'),
-- Habilidades Blandas (Soft Skills)
('20000000-0000-0000-0000-000000000067', 'Comunicación', 'BLANDA'),
('20000000-0000-0000-0000-000000000068', 'Liderazgo', 'BLANDA'),
('20000000-0000-0000-0000-000000000069', 'Trabajo en Equipo', 'BLANDA'),
('20000000-0000-0000-0000-000000000070', 'Resolución de Problemas', 'BLANDA'),
('20000000-0000-0000-0000-000000000071', 'Pensamiento Crítico', 'BLANDA'),
('20000000-0000-0000-0000-000000000072', 'Adaptabilidad', 'BLANDA'),
('20000000-0000-0000-0000-000000000073', 'Gestión del Tiempo', 'BLANDA'),
('20000000-0000-0000-0000-000000000074', 'Resolución de Conflictos', 'BLANDA'),
('20000000-0000-0000-0000-000000000075', 'Mentoría', 'BLANDA'),
('20000000-0000-0000-0000-000000000076', 'Gestión de Stakeholders', 'BLANDA'),
('20000000-0000-0000-0000-000000000077', 'Gestión de Producto', 'BLANDA'),
('20000000-0000-0000-0000-000000000078', 'Coaching Ágil', 'BLANDA'),
('20000000-0000-0000-0000-000000000079', 'Oratoria', 'BLANDA'),
('20000000-0000-0000-0000-000000000080', 'Negociación', 'BLANDA'),
('20000000-0000-0000-0000-000000000081', 'Inteligencia Emocional', 'BLANDA'),
('20000000-0000-0000-0000-000000000082', 'Creatividad', 'BLANDA'),
('20000000-0000-0000-0000-000000000083', 'Toma de Decisiones', 'BLANDA'),
('20000000-0000-0000-0000-000000000084', 'Colaboración Interfuncional', 'BLANDA');

-- Empresas (Companies)
INSERT INTO company (id, created_at, culture_description, industry, is_partner, logo, name, size, website)
VALUES ('50000000-0000-0000-0000-000000000001', '2023-01-01 10:00:00+00',
        'Entorno ágil, dinámico y enfocado en la innovación.', 'Tecnología', TRUE, 'logo_innova.png',
        'Innovación Tecnológica S.A.', '50-200', 'https://innovaciontec.com.es'),
       ('50000000-0000-0000-0000-000000000002', '2023-01-15 10:00:00+00', 'Cultura basada en datos y mejora continua.',
        'Finanzas', FALSE, 'logo_datos.png', 'Soluciones de Datos LLC', '201-500', 'https://solucionesdatos.com');

-- Datos de Usuario (User Data: 10 Candidatos + 2 Reclutadores)
INSERT INTO user_data (id, created_at, email, password, user_type)
VALUES ('10000000-0000-0000-0000-000000000001', '2023-01-01 10:00:00+00', 'alicia@ejemplo.com', 'hashed1', 'CANDIDATO'),
       ('10000000-0000-0000-0000-000000000002', '2023-01-02 10:00:00+00', 'roberto@ejemplo.com', 'hashed2',
        'CANDIDATO'),
       ('10000000-0000-0000-0000-000000000003', '2023-01-03 10:00:00+00', 'carlos@ejemplo.com', 'hashed3', 'CANDIDATO'),
       ('10000000-0000-0000-0000-000000000004', '2023-01-04 10:00:00+00', 'diana@ejemplo.com', 'hashed4', 'CANDIDATO'),
       ('10000000-0000-0000-0000-000000000005', '2023-01-05 10:00:00+00', 'esteban@ejemplo.com', 'hashed5',
        'CANDIDATO'),
       ('10000000-0000-0000-0000-000000000006', '2023-01-06 10:00:00+00', 'flavia@ejemplo.com', 'hashed6', 'CANDIDATO'),
       ('10000000-0000-0000-0000-000000000007', '2023-01-07 10:00:00+00', 'guillermo@ejemplo.com', 'hashed7',
        'CANDIDATO'),
       ('10000000-0000-0000-0000-000000000008', '2023-01-08 10:00:00+00', 'helena@ejemplo.com', 'hashed8', 'CANDIDATO'),
       ('10000000-0000-0000-0000-000000000009', '2023-01-09 10:00:00+00', 'ignacio@ejemplo.com', 'hashed9',
        'CANDIDATO'),
       ('10000000-0000-0000-0000-000000000010', '2023-01-10 10:00:00+00', 'julieta@ejemplo.com', 'hashed10',
        'CANDIDATO'),
       ('60000000-0000-0000-0000-000000000001', '2023-01-01 10:00:00+00', 'rrhh@innovaciontec.com', 'hashed11',
        'RECLUTADOR'),
       ('60000000-0000-0000-0000-000000000002', '2023-01-15 10:00:00+00', 'seleccion@solucionesdatos.com', 'hashed12',
        'RECLUTADOR');


-- ==========================================
-- 2. DEPENDENCIAS DE NIVEL 1
-- ==========================================

-- Candidatos
INSERT INTO candidate (id, current_role_title, full_name, headline, identity_verified, linked_in, location, phone,
                       profile_completion, profile_photo)
VALUES ('10000000-0000-0000-0000-000000000001', 'Desarrolladora Java Senior', 'Alicia Gómez',
        'Construyendo arquitecturas backend escalables.', TRUE, 'linkedin.com/in/aliciagomez', 'Madrid, España',
        '+34-600-000-001', 100, 'alicia.jpg'),
       ('10000000-0000-0000-0000-000000000002', 'Ingeniero Frontend', 'Roberto García',
        'Entusiasta de React y componentes web.', TRUE, 'linkedin.com/in/robertogarcia', 'Ciudad de México, México',
        '+52-55-1234-5678', 95, 'roberto.jpg'),
       ('10000000-0000-0000-0000-000000000003', 'Científico de Datos', 'Carlos Fernández',
        'Resolviendo problemas a través de los datos.', TRUE, 'linkedin.com/in/carlosfernandez',
        'Buenos Aires, Argentina', '+54-11-4321-8765', 100, 'carlos.jpg'),
       ('10000000-0000-0000-0000-000000000004', 'Arquitecta Cloud', 'Diana Martínez',
        'Diseñando infraestructura resiliente en AWS.', TRUE, 'linkedin.com/in/dianamartinez', 'Bogotá, Colombia',
        '+57-300-111-2222', 100, 'diana.jpg'),
       ('10000000-0000-0000-0000-000000000005', 'Diseñador UI/UX', 'Esteban Rodríguez',
        'Creando experiencias de usuario accesibles.', FALSE, 'linkedin.com/in/estebanrodriguez', 'Santiago, Chile',
        '+56-9-8888-7777', 80, 'esteban.jpg'),
       ('10000000-0000-0000-0000-000000000006', 'Desarrolladora Full Stack', 'Flavia López',
        'Conectando el front con el back.', TRUE, 'linkedin.com/in/flavialopez', 'Lima, Perú', '+51-999-888-777', 100,
        'flavia.jpg'),
       ('10000000-0000-0000-0000-000000000007', 'Desarrollador Junior', 'Guillermo Pérez',
        'Aprendiendo desarrollo web constantemente.', FALSE, 'linkedin.com/in/guillermoperez', 'Quito, Ecuador',
        '+593-9-1234-5678', 75, 'guillermo.jpg'),
       ('10000000-0000-0000-0000-000000000008', 'Administradora de Base de Datos', 'Helena Sánchez',
        'Optimizando consultas SQL diariamente.', TRUE, 'linkedin.com/in/helenasanchez', 'Montevideo, Uruguay',
        '+598-99-123-456', 90, 'helena.jpg'),
       ('10000000-0000-0000-0000-000000000009', 'Ingeniero DevOps', 'Ignacio Romero',
        'Automatizando todo el ciclo de vida del software.', TRUE, 'linkedin.com/in/ignacioromero',
        'San José, Costa Rica', '+506-8888-9999', 100, 'ignacio.jpg'),
       ('10000000-0000-0000-0000-000000000010', 'Líder Técnico', 'Julieta Torres',
        'Liderando equipos de ingeniería de alto rendimiento.', TRUE, 'linkedin.com/in/julietatorres', 'Remoto',
        '+34-600-000-010', 100, 'julieta.jpg');

-- Reclutadores
INSERT INTO recruiter (id, company_id)
VALUES ('60000000-0000-0000-0000-000000000001', '50000000-0000-0000-0000-000000000001'),
       ('60000000-0000-0000-0000-000000000002', '50000000-0000-0000-0000-000000000002');


-- ==========================================
-- 3. DEPENDENCIAS DE NIVEL 2 (Asociadas al Candidato)
-- ==========================================

-- Experiencia Laboral (Work Experience)
INSERT INTO work_experience (id, candidate_id, company, description, position, start_date, end_date, is_current)
VALUES ('30000000-0000-0000-0000-000000000001', '10000000-0000-0000-0000-000000000001', 'Finanzas Globales S.A.',
        'Mantenimiento de microservicios críticos en Java.', 'Desarrolladora Backend', '2019-01-01', NULL, TRUE),
       ('30000000-0000-0000-0000-000000000002', '10000000-0000-0000-0000-000000000002', 'Estudio Web',
        'Construcción de componentes interactivos con React.', 'Desarrollador Frontend', '2020-05-01', NULL, TRUE),
       ('30000000-0000-0000-0000-000000000003', '10000000-0000-0000-0000-000000000003', 'Datos al Día',
        'Creación de modelos predictivos usando Python.', 'Científico de Datos', '2018-08-01', '2023-01-01', FALSE),
       ('30000000-0000-0000-0000-000000000004', '10000000-0000-0000-0000-000000000004', 'NubeSistemas',
        'Migración de la arquitectura tradicional a AWS.', 'Arquitecta Cloud', '2016-03-01', NULL, TRUE),
       ('30000000-0000-0000-0000-000000000005', '10000000-0000-0000-0000-000000000005', 'Agencia Creativa',
        'Liderazgo en diseño de producto utilizando Figma.', 'Diseñador Senior', '2021-01-01', NULL, TRUE),
       ('30000000-0000-0000-0000-000000000006', '10000000-0000-0000-0000-000000000006', 'Startup X',
        'Integración de interfaces en React con APIs en Java.', 'Desarrolladora Full Stack', '2020-10-01', NULL, TRUE),
       ('30000000-0000-0000-0000-000000000007', '10000000-0000-0000-0000-000000000007', 'Laboratorio Universitario',
        'Desarrollo de scripts básicos en Python.', 'Pasante', '2022-06-01', '2022-09-01', FALSE),
       ('30000000-0000-0000-0000-000000000008', '10000000-0000-0000-0000-000000000008', 'Banco Nacional',
        'Administración y respaldo de bases de datos PostgreSQL.', 'DBA', '2015-02-01', NULL, TRUE),
       ('30000000-0000-0000-0000-000000000009', '10000000-0000-0000-0000-000000000009', 'Comercio Electrónico',
        'Configuración de pipelines CI/CD y automatización.', 'Ingeniero DevOps', '2019-11-01', NULL, TRUE),
       ('30000000-0000-0000-0000-000000000010', '10000000-0000-0000-0000-000000000010', 'Empresa Tech',
        'Mentoría y liderazgo técnico de 15 ingenieros.', 'Líder Técnico', '2014-07-01', NULL, TRUE);

-- Habilidades de Candidato (Candidate Skills) - Referencias ajustadas al catálogo en Java
INSERT INTO candidate_skill (id, candidate_id, skill_id, consolidated_level, consolidated_score, experience_range,
                             created_at)
VALUES ('25000000-0000-0000-0000-000000000001', '10000000-0000-0000-0000-000000000001',
        '20000000-0000-0000-0000-000000000001', 'AVANZADO', 9.5, '5+ Años', '2023-01-01 10:00:00+00'), -- Alicia: Java
       ('25000000-0000-0000-0000-000000000002', '10000000-0000-0000-0000-000000000002',
        '20000000-0000-0000-0000-000000000010', 'AVANZADO', 9.0, '3-5 Años',
        '2023-01-02 10:00:00+00'),                                                                     -- Roberto: React
       ('25000000-0000-0000-0000-000000000003', '10000000-0000-0000-0000-000000000003',
        '20000000-0000-0000-0000-000000000004', 'EXPERTO', 9.8, '5+ Años', '2023-01-03 10:00:00+00'),  -- Carlos: Python
       ('25000000-0000-0000-0000-000000000004', '10000000-0000-0000-0000-000000000004',
        '20000000-0000-0000-0000-000000000040', 'EXPERTO', 9.9, '5+ Años', '2023-01-04 10:00:00+00'),  -- Diana: AWS
       ('25000000-0000-0000-0000-000000000005', '10000000-0000-0000-0000-000000000005',
        '20000000-0000-0000-0000-000000000058', 'AVANZADO', 8.5, '1-3 Años',
        '2023-01-05 10:00:00+00'),                                                                     -- Esteban: Figma
       ('25000000-0000-0000-0000-000000000006', '10000000-0000-0000-0000-000000000006',
        '20000000-0000-0000-0000-000000000010', 'INTERMEDIO', 7.0, '1-3 Años',
        '2023-01-06 10:00:00+00'),                                                                     -- Flavia: React
       ('25000000-0000-0000-0000-000000000007', '10000000-0000-0000-0000-000000000007',
        '20000000-0000-0000-0000-000000000004', 'PRINCIPIANTE', 4.5, '< 1 Año',
        '2023-01-07 10:00:00+00'),                                                                     -- Guillermo: Python
       ('25000000-0000-0000-0000-000000000008', '10000000-0000-0000-0000-000000000008',
        '20000000-0000-0000-0000-000000000030', 'EXPERTO', 9.7, '5+ Años',
        '2023-01-08 10:00:00+00'),                                                                     -- Helena: PostgreSQL
       ('25000000-0000-0000-0000-000000000009', '10000000-0000-0000-0000-000000000009',
        '20000000-0000-0000-0000-000000000064', 'AVANZADO', 8.8, '3-5 Años',
        '2023-01-09 10:00:00+00'),                                                                     -- Ignacio: DevOps
       ('25000000-0000-0000-0000-000000000010', '10000000-0000-0000-0000-000000000010',
        '20000000-0000-0000-0000-000000000068', 'EXPERTO', 9.6, '5+ Años', '2023-01-10 10:00:00+00');
-- Julieta: Liderazgo

-- Proyectos (Projects)
INSERT INTO project (id, candidate_id, title, description, link)
VALUES ('40000000-0000-0000-0000-000000000001', '10000000-0000-0000-0000-000000000001', 'Pasarela de Pagos',
        'Integración de API de Stripe mediante Spring Boot.', 'github.com/alicia/pagos'),
       ('40000000-0000-0000-0000-000000000003', '10000000-0000-0000-0000-000000000003', 'Predictor de Mercado',
        'Modelo de aprendizaje automático predictivo.', 'github.com/carlos/mercado');

-- Reputación del Validador (Validator Reputation)
INSERT INTO validator_reputation (user_id, identity_verified, platform_years, reputation_level, reputation_score,
                                  seniority, success_rate, total_validations)
VALUES ('10000000-0000-0000-0000-000000000003', TRUE, 2, 'VALIDADOR_EXPERTO', 4.8, 'SENIOR', 95, 20);


-- ==========================================
-- 4. OFERTAS DE TRABAJO Y ECOSISTEMA
-- ==========================================

-- Ofertas de Trabajo (Job Offers)
INSERT INTO job_offer (id, company_id, recruiter_id, created_at, title, description, benefits, location, modality,
                       salary_min, salary_max, seniority, status)
VALUES ('70000000-0000-0000-0000-000000000001', '50000000-0000-0000-0000-000000000001',
        '60000000-0000-0000-0000-000000000001', '2023-02-01 10:00:00+00', 'Desarrollador Java Senior',
        'Buscamos un experto en Java para liderar nuestra arquitectura backend.',
        'Seguro médico, plan de retiro, bonos.', 'Madrid (Híbrido)', 'TIEMPO_COMPLETO', 50000.00, 70000.00, 'SENIOR',
        'ABIERTA'),
       ('70000000-0000-0000-0000-000000000002', '50000000-0000-0000-0000-000000000002',
        '60000000-0000-0000-0000-000000000002', '2023-02-15 10:00:00+00', 'Científico de Datos Mid-Level',
        'Únete a nuestro equipo de análisis predictivo.', 'Trabajo 100% remoto, 25 días de vacaciones.', 'Remoto',
        'TIEMPO_COMPLETO', 40000.00, 60000.00, 'SEMI_SENIOR', 'ABIERTA');

-- Habilidades de la Oferta (Offer Skills)
INSERT INTO offer_skill (offer_id, skill_id, requirement)
VALUES ('70000000-0000-0000-0000-000000000001', '20000000-0000-0000-0000-000000000001', 'OBLIGATORIO'), -- Requiere Java
       ('70000000-0000-0000-0000-000000000002', '20000000-0000-0000-0000-000000000004', 'OBLIGATORIO');
-- Requiere Python

-- Emparejamiento (Match - Alicia es emparejada con la Oferta 1)
INSERT INTO match (id, candidate_id, offer_id, created_at, match_score, profile_revealed, revealed_at, status)
VALUES ('80000000-0000-0000-0000-000000000001', '10000000-0000-0000-0000-000000000001',
        '70000000-0000-0000-0000-000000000001', '2023-02-05 10:00:00+00', 98, FALSE, NULL, 'PENDIENTE');

-- Hilo Anónimo (Anonymous Thread)
INSERT INTO anonymous_thread (id, candidate_id, offer_id, anonymous_code, category, created_at, status)
VALUES ('90000000-0000-0000-0000-000000000001', '10000000-0000-0000-0000-000000000001',
        '70000000-0000-0000-0000-000000000001', 'XYZ987', 'CONSULTA_GENERAL', '2023-02-06 10:00:00+00', 'ACTIVO');

-- Mensaje Anónimo (Anonymous Message)
INSERT INTO anonymous_message (id, thread_id, content, created_at, sender_type)
VALUES ('A0000000-0000-0000-0000-000000000001', '90000000-0000-0000-0000-000000000001',
        'Hola, ¿el esquema de trabajo híbrido requiere ir a la oficina días específicos?', '2023-02-06 10:05:00+00',
        'CANDIDATO');


-- ==========================================
-- 5. VALIDACIONES (Sistema de revisión entre pares)
-- ==========================================

-- Solicitud de Validación (Guillermo le pide a Carlos que valide sus habilidades en Python)
INSERT INTO validation_request (id, requester_id, skill_id, validator_id, created_at, message, relation_type, status)
VALUES ('B0000000-0000-0000-0000-000000000001', '10000000-0000-0000-0000-000000000007',
        '20000000-0000-0000-0000-000000000004', '10000000-0000-0000-0000-000000000003', '2023-03-01 10:00:00+00',
        'Hola Carlos, ¿podrías validar mi trabajo reciente procesando datos con Python?', 'COMPAÑERO_DE_TRABAJO',
        'COMPLETADO');

-- Validación (Carlos completa la validación para Guillermo)
INSERT INTO validation (id, validation_request_id, candidate_id, skill_id, validator_id, assigned_level, comment,
                        created_at)
VALUES ('C0000000-0000-0000-0000-000000000001', 'B0000000-0000-0000-0000-000000000001',
        '10000000-0000-0000-0000-000000000007', '20000000-0000-0000-0000-000000000004',
        '10000000-0000-0000-0000-000000000003', 'PRINCIPIANTE',
        'Guillermo tiene una base sólida y aprende muy rápido. Excelente actitud.', '2023-03-02 10:00:00+00');
-- ============================================================
-- V2 — Datos semilla para desarrollo / testing
-- Los valores de enum coinciden con los code() de los convertidores Java.
-- Todas las contraseñas son BCrypt de "password123".
-- Todos los INSERTs usan ON CONFLICT DO NOTHING para idempotencia.
-- ============================================================

-- ==========================================================
-- 1. TABLAS INDEPENDIENTES
-- ==========================================================

-- ----- Skills (catálogo completo) -----
INSERT INTO skill (id, name, type)
VALUES
-- Habilidades técnicas
('20000000-0000-0000-0000-000000000001', 'Java',                 'TECH'),
('20000000-0000-0000-0000-000000000002', 'Spring Boot',          'TECH'),
('20000000-0000-0000-0000-000000000003', 'Kotlin',               'TECH'),
('20000000-0000-0000-0000-000000000004', 'Python',               'TECH'),
('20000000-0000-0000-0000-000000000005', 'Django',               'TECH'),
('20000000-0000-0000-0000-000000000006', 'FastAPI',              'TECH'),
('20000000-0000-0000-0000-000000000007', 'Node.js',              'TECH'),
('20000000-0000-0000-0000-000000000008', 'TypeScript',           'TECH'),
('20000000-0000-0000-0000-000000000009', 'JavaScript',           'TECH'),
('20000000-0000-0000-0000-000000000010', 'React',                'TECH'),
('20000000-0000-0000-0000-000000000011', 'Next.js',              'TECH'),
('20000000-0000-0000-0000-000000000012', 'Angular',              'TECH'),
('20000000-0000-0000-0000-000000000013', 'Vue.js',               'TECH'),
('20000000-0000-0000-0000-000000000014', 'HTML',                 'TECH'),
('20000000-0000-0000-0000-000000000015', 'CSS',                  'TECH'),
('20000000-0000-0000-0000-000000000016', 'Tailwind CSS',         'TECH'),
('20000000-0000-0000-0000-000000000017', 'Go',                   'TECH'),
('20000000-0000-0000-0000-000000000018', 'Rust',                 'TECH'),
('20000000-0000-0000-0000-000000000019', 'C Sharp',              'TECH'),
('20000000-0000-0000-0000-000000000020', 'DotNet',               'TECH'),
('20000000-0000-0000-0000-000000000021', 'PHP',                  'TECH'),
('20000000-0000-0000-0000-000000000022', 'Ruby',                 'TECH'),
('20000000-0000-0000-0000-000000000023', 'Ruby on Rails',        'TECH'),
('20000000-0000-0000-0000-000000000024', 'Swift',                'TECH'),
('20000000-0000-0000-0000-000000000025', 'iOS Development',      'TECH'),
('20000000-0000-0000-0000-000000000026', 'Android Development',  'TECH'),
('20000000-0000-0000-0000-000000000027', 'Flutter',              'TECH'),
('20000000-0000-0000-0000-000000000028', 'React Native',         'TECH'),
('20000000-0000-0000-0000-000000000029', 'SQL',                  'TECH'),
('20000000-0000-0000-0000-000000000030', 'PostgreSQL',           'TECH'),
('20000000-0000-0000-0000-000000000031', 'MySQL',                'TECH'),
('20000000-0000-0000-0000-000000000032', 'MongoDB',              'TECH'),
('20000000-0000-0000-0000-000000000033', 'Redis',                'TECH'),
('20000000-0000-0000-0000-000000000034', 'Elasticsearch',        'TECH'),
('20000000-0000-0000-0000-000000000035', 'GraphQL',              'TECH'),
('20000000-0000-0000-0000-000000000036', 'REST APIs',            'TECH'),
('20000000-0000-0000-0000-000000000037', 'Microservices',        'TECH'),
('20000000-0000-0000-0000-000000000038', 'Docker',               'TECH'),
('20000000-0000-0000-0000-000000000039', 'Kubernetes',           'TECH'),
('20000000-0000-0000-0000-000000000040', 'AWS',                  'TECH'),
('20000000-0000-0000-0000-000000000041', 'Azure',                'TECH'),
('20000000-0000-0000-0000-000000000042', 'Google Cloud',         'TECH'),
('20000000-0000-0000-0000-000000000043', 'Terraform',            'TECH'),
('20000000-0000-0000-0000-000000000044', 'CI/CD',                'TECH'),
('20000000-0000-0000-0000-000000000045', 'Git',                  'TECH'),
('20000000-0000-0000-0000-000000000046', 'Linux',                'TECH'),
('20000000-0000-0000-0000-000000000047', 'Apache Kafka',         'TECH'),
('20000000-0000-0000-0000-000000000048', 'RabbitMQ',             'TECH'),
('20000000-0000-0000-0000-000000000049', 'System Design',        'TECH'),
('20000000-0000-0000-0000-000000000050', 'Data Engineering',     'TECH'),
('20000000-0000-0000-0000-000000000051', 'Apache Spark',         'TECH'),
('20000000-0000-0000-0000-000000000052', 'Machine Learning',     'TECH'),
('20000000-0000-0000-0000-000000000053', 'TensorFlow',           'TECH'),
('20000000-0000-0000-0000-000000000054', 'PyTorch',              'TECH'),
('20000000-0000-0000-0000-000000000055', 'Data Analysis',        'TECH'),
('20000000-0000-0000-0000-000000000056', 'Power BI',             'TECH'),
('20000000-0000-0000-0000-000000000057', 'Tableau',              'TECH'),
('20000000-0000-0000-0000-000000000058', 'Figma',                'TECH'),
('20000000-0000-0000-0000-000000000059', 'UI Design',            'TECH'),
('20000000-0000-0000-0000-000000000060', 'UX Research',          'TECH'),
('20000000-0000-0000-0000-000000000061', 'Selenium',             'TECH'),
('20000000-0000-0000-0000-000000000062', 'QA Automation',        'TECH'),
('20000000-0000-0000-0000-000000000063', 'Cybersecurity',        'TECH'),
('20000000-0000-0000-0000-000000000064', 'DevOps',               'TECH'),
('20000000-0000-0000-0000-000000000065', 'Scrum',                'TECH'),
('20000000-0000-0000-0000-000000000066', 'Jira',                 'TECH'),
-- Habilidades blandas
('20000000-0000-0000-0000-000000000067', 'Communication',                  'SOFT'),
('20000000-0000-0000-0000-000000000068', 'Leadership',                     'SOFT'),
('20000000-0000-0000-0000-000000000069', 'Teamwork',                       'SOFT'),
('20000000-0000-0000-0000-000000000070', 'Problem Solving',                'SOFT'),
('20000000-0000-0000-0000-000000000071', 'Critical Thinking',              'SOFT'),
('20000000-0000-0000-0000-000000000072', 'Adaptability',                   'SOFT'),
('20000000-0000-0000-0000-000000000073', 'Time Management',                'SOFT'),
('20000000-0000-0000-0000-000000000074', 'Conflict Resolution',            'SOFT'),
('20000000-0000-0000-0000-000000000075', 'Mentoring',                      'SOFT'),
('20000000-0000-0000-0000-000000000076', 'Stakeholder Management',         'SOFT'),
('20000000-0000-0000-0000-000000000077', 'Product Management',             'SOFT'),
('20000000-0000-0000-0000-000000000078', 'Agile Coaching',                 'SOFT'),
('20000000-0000-0000-0000-000000000079', 'Public Speaking',                'SOFT'),
('20000000-0000-0000-0000-000000000080', 'Negotiation',                    'SOFT'),
('20000000-0000-0000-0000-000000000081', 'Emotional Intelligence',         'SOFT'),
('20000000-0000-0000-0000-000000000082', 'Creativity',                     'SOFT'),
('20000000-0000-0000-0000-000000000083', 'Decision Making',                'SOFT'),
('20000000-0000-0000-0000-000000000084', 'Cross-functional Collaboration', 'SOFT'),
-- Habilidades de Marketing Digital (Demo Final)
('20000000-0000-0000-0000-000000000085', 'Marketing Digital',              'TECH'),
('20000000-0000-0000-0000-000000000086', 'Gestión de Redes Sociales',      'TECH'),
('20000000-0000-0000-0000-000000000087', 'Meta Ads',                       'TECH'),
('20000000-0000-0000-0000-000000000088', 'Google Analytics',               'TECH'),
('20000000-0000-0000-0000-000000000089', 'Análisis de Métricas',           'TECH'),
('20000000-0000-0000-0000-000000000090', 'Gestión de Campañas Digitales',  'TECH'),
('20000000-0000-0000-0000-000000000091', 'Google Ads',                     'TECH'),
('20000000-0000-0000-0000-000000000092', 'SEO',                            'TECH'),
-- Trabajo en Equipo en español (alias de Teamwork para la demo)
('20000000-0000-0000-0000-000000000093', 'Trabajo en Equipo',              'SOFT')
ON CONFLICT (id) DO NOTHING;


-- ----- Empresas -----
INSERT INTO company (id, created_at, culture_description, industry, is_partner, logo, name, size, website)
VALUES
('50000000-0000-0000-0000-000000000001', '2023-01-01 10:00:00+00',
 'Entorno ágil y dinámico enfocado en la innovación tecnológica.', 'TECH', TRUE,
 'logo_innova.png', 'Innovación Tecnológica S.A.', '51-200', 'https://innovaciontec.com'),
('50000000-0000-0000-0000-000000000002', '2023-01-15 10:00:00+00',
 'Cultura orientada a datos con foco en la mejora continua.', 'FINANCE', FALSE,
 'logo_datos.png', 'Soluciones de Datos LLC', '201-1000', 'https://solucionesdatos.com'),
('50000000-0000-0000-0000-000000000003', '2023-03-01 10:00:00+00',
 'Cultura startup de ritmo acelerado, obsesionada con el cliente.', 'ECOMMERCE', TRUE,
 'logo_digi.png', 'DigiCommerce S.R.L.', '11-50', 'https://digicommerce.com'),
-- TechNova (Demo Final)
('50000000-0000-0000-0000-000000000004', '2023-04-01 10:00:00+00',
 'Empresa tecnológica enfocada en soluciones SaaS para pequeñas y medianas empresas, con un equipo de Marketing Digital dedicado a adquisición de usuarios, campañas de performance y analítica.', 'TECH', TRUE,
 'logo_technova.png', 'TechNova', '51-200', 'https://technova.com')
ON CONFLICT (id) DO NOTHING;


-- ==========================================================
-- 2. USUARIOS  (10 Candidatos + 3 Reclutadores)
--    Contraseña para TODOS los usuarios: password123
-- ==========================================================

INSERT INTO user_data (id, created_at, email, password, user_type)
VALUES
-- Candidatos
('10000000-0000-0000-0000-000000000001', '2023-01-01 10:00:00+00', 'alicia@example.com',     '$2a$10$bMErL2WzvER8cRbZoO.HSe3VgvSMTqRf4mo8GBdqmwBSElqbdeXp6', 'CANDIDATE'),
('10000000-0000-0000-0000-000000000002', '2023-01-02 10:00:00+00', 'roberto@example.com',    '$2a$10$bMErL2WzvER8cRbZoO.HSe3VgvSMTqRf4mo8GBdqmwBSElqbdeXp6', 'CANDIDATE'),
('10000000-0000-0000-0000-000000000003', '2023-01-03 10:00:00+00', 'carlos@example.com',     '$2a$10$bMErL2WzvER8cRbZoO.HSe3VgvSMTqRf4mo8GBdqmwBSElqbdeXp6', 'CANDIDATE'),
('10000000-0000-0000-0000-000000000004', '2023-01-04 10:00:00+00', 'diana@example.com',      '$2a$10$bMErL2WzvER8cRbZoO.HSe3VgvSMTqRf4mo8GBdqmwBSElqbdeXp6', 'CANDIDATE'),
('10000000-0000-0000-0000-000000000005', '2023-01-05 10:00:00+00', 'esteban@example.com',    '$2a$10$bMErL2WzvER8cRbZoO.HSe3VgvSMTqRf4mo8GBdqmwBSElqbdeXp6', 'CANDIDATE'),
('10000000-0000-0000-0000-000000000006', '2023-01-06 10:00:00+00', 'flavia@example.com',     '$2a$10$bMErL2WzvER8cRbZoO.HSe3VgvSMTqRf4mo8GBdqmwBSElqbdeXp6', 'CANDIDATE'),
('10000000-0000-0000-0000-000000000007', '2023-01-07 10:00:00+00', 'guillermo@example.com',  '$2a$10$bMErL2WzvER8cRbZoO.HSe3VgvSMTqRf4mo8GBdqmwBSElqbdeXp6', 'CANDIDATE'),
('10000000-0000-0000-0000-000000000008', '2023-01-08 10:00:00+00', 'helena@example.com',     '$2a$10$bMErL2WzvER8cRbZoO.HSe3VgvSMTqRf4mo8GBdqmwBSElqbdeXp6', 'CANDIDATE'),
('10000000-0000-0000-0000-000000000009', '2023-01-09 10:00:00+00', 'ignacio@example.com',    '$2a$10$bMErL2WzvER8cRbZoO.HSe3VgvSMTqRf4mo8GBdqmwBSElqbdeXp6', 'CANDIDATE'),
('10000000-0000-0000-0000-000000000010', '2023-01-10 10:00:00+00', 'julieta@example.com',    '$2a$10$bMErL2WzvER8cRbZoO.HSe3VgvSMTqRf4mo8GBdqmwBSElqbdeXp6', 'CANDIDATE'),
-- Candidatos Demo Final
('10000000-0000-0000-0000-000000000011', '2023-04-01 10:00:00+00', 'martina@example.com',    '$2a$10$bMErL2WzvER8cRbZoO.HSe3VgvSMTqRf4mo8GBdqmwBSElqbdeXp6', 'CANDIDATE'),
('10000000-0000-0000-0000-000000000012', '2023-04-01 10:00:00+00', 'tomas@example.com',      '$2a$10$bMErL2WzvER8cRbZoO.HSe3VgvSMTqRf4mo8GBdqmwBSElqbdeXp6', 'CANDIDATE'),
('10000000-0000-0000-0000-000000000013', '2023-04-01 10:00:00+00', 'leandro@example.com',    '$2a$10$bMErL2WzvER8cRbZoO.HSe3VgvSMTqRf4mo8GBdqmwBSElqbdeXp6', 'CANDIDATE'),
-- Candidatos adicionales para match con oferta Marketing Digital
('10000000-0000-0000-0000-000000000014', '2023-04-05 10:00:00+00', 'sofia@example.com',      '$2a$10$bMErL2WzvER8cRbZoO.HSe3VgvSMTqRf4mo8GBdqmwBSElqbdeXp6', 'CANDIDATE'),
('10000000-0000-0000-0000-000000000015', '2023-04-06 10:00:00+00', 'nicolas@example.com',    '$2a$10$bMErL2WzvER8cRbZoO.HSe3VgvSMTqRf4mo8GBdqmwBSElqbdeXp6', 'CANDIDATE'),
('10000000-0000-0000-0000-000000000016', '2023-04-07 10:00:00+00', 'valentina@example.com',  '$2a$10$bMErL2WzvER8cRbZoO.HSe3VgvSMTqRf4mo8GBdqmwBSElqbdeXp6', 'CANDIDATE'),
-- Reclutadores
('60000000-0000-0000-0000-000000000001', '2023-01-01 10:00:00+00', 'rrhh@innovaciontec.com',       '$2a$10$bMErL2WzvER8cRbZoO.HSe3VgvSMTqRf4mo8GBdqmwBSElqbdeXp6', 'RECRUITER'),
('60000000-0000-0000-0000-000000000002', '2023-01-15 10:00:00+00', 'seleccion@solucionesdatos.com', '$2a$10$bMErL2WzvER8cRbZoO.HSe3VgvSMTqRf4mo8GBdqmwBSElqbdeXp6', 'RECRUITER'),
('60000000-0000-0000-0000-000000000003', '2023-03-01 10:00:00+00', 'contratacion@digicommerce.com', '$2a$10$bMErL2WzvER8cRbZoO.HSe3VgvSMTqRf4mo8GBdqmwBSElqbdeXp6', 'RECRUITER'),
-- Reclutadora TechNova (Demo Final)
('60000000-0000-0000-0000-000000000004', '2023-04-01 10:00:00+00', 'laura.fernandez@technova.com',  '$2a$10$bMErL2WzvER8cRbZoO.HSe3VgvSMTqRf4mo8GBdqmwBSElqbdeXp6', 'RECRUITER')
ON CONFLICT (id) DO NOTHING;


-- ==========================================================
-- 3. DEPENDIENTES NIVEL 1
-- ==========================================================

-- ----- Candidatos -----
INSERT INTO candidate (id, current_role_title, full_name, headline, identity_verified,
                       linked_in, location, phone, profile_completion, profile_photo)
VALUES
('10000000-0000-0000-0000-000000000001', 'Desarrolladora Java Senior', 'Alicia Gómez',
 'Construyendo arquitecturas backend escalables.',            TRUE,  'linkedin.com/in/aliciagomez',      'Madrid, España',           '+34-600-000-001', 100, 'alicia.jpg'),
('10000000-0000-0000-0000-000000000002', 'Ingeniero Frontend',         'Roberto García',
 'Entusiasta de React y los componentes web.',                TRUE,  'linkedin.com/in/robertogarcia',    'Ciudad de México, México',     '+52-55-1234-5678', 95, 'roberto.jpg'),
('10000000-0000-0000-0000-000000000003', 'Científico de Datos',        'Carlos Fernández',
 'Resolviendo problemas a través de los datos.',              TRUE,  'linkedin.com/in/carlosfernandez',  'Buenos Aires, Argentina',  '+54-11-4321-8765', 100, 'carlos.jpg'),
('10000000-0000-0000-0000-000000000004', 'Arquitecta Cloud',           'Diana Martínez',
 'Diseñando infraestructura resiliente en AWS.',              TRUE,  'linkedin.com/in/dianamartinez',    'Bogotá, Colombia',         '+57-300-111-2222', 100, 'diana.jpg'),
('10000000-0000-0000-0000-000000000005', 'Diseñador UI/UX',            'Esteban Rodríguez',
 'Creando experiencias de usuario accesibles.',               FALSE, 'linkedin.com/in/estebanrodriguez', 'Santiago, Chile',          '+56-9-8888-7777',  80, 'esteban.jpg'),
('10000000-0000-0000-0000-000000000006', 'Desarrolladora Full Stack',  'Flavia López',
 'Conectando el frontend con el backend.',                    TRUE,  'linkedin.com/in/flavialopez',      'Lima, Perú',               '+51-999-888-777',  100, 'flavia.jpg'),
('10000000-0000-0000-0000-000000000007', 'Desarrollador Junior',       'Guillermo Pérez',
 'Aprendiendo desarrollo web constantemente.',                FALSE, 'linkedin.com/in/guillermoperez',   'Quito, Ecuador',           '+593-9-1234-5678', 75, 'guillermo.jpg'),
('10000000-0000-0000-0000-000000000008', 'Administradora de BD',       'Helena Sánchez',
 'Optimizando consultas SQL día a día.',                      TRUE,  'linkedin.com/in/helenasanchez',    'Montevideo, Uruguay',      '+598-99-123-456',  90, 'helena.jpg'),
('10000000-0000-0000-0000-000000000009', 'Ingeniero DevOps',           'Ignacio Romero',
 'Automatizando todo el ciclo de vida del software.',         TRUE,  'linkedin.com/in/ignacioromero',    'San José, Costa Rica',     '+506-8888-9999',  100, 'ignacio.jpg'),
('10000000-0000-0000-0000-000000000010', 'Líder Técnico',              'Julieta Torres',
 'Liderando equipos de ingeniería de alto rendimiento.',      TRUE,  'linkedin.com/in/julietatorres',    'Remoto',                   '+34-600-000-010', 100, 'julieta.jpg'),
-- Candidatos Demo Final
('10000000-0000-0000-0000-000000000011', 'Estudiante de Marketing',    'Martina López',
 'Estudiante de Marketing apasionada por el marketing digital y el análisis de datos. Durante una pasantía participé en campañas digitales, gestión de redes sociales y elaboración de reportes. Busco mi primera experiencia laboral efectiva.', TRUE, 'linkedin.com/in/martinalopez', 'Buenos Aires, Argentina', '+54-11-5555-1111', 90, 'martina.jpg'),
('10000000-0000-0000-0000-000000000012', 'Estudiante de Marketing',    'Tomás Fernández',
 'Estudiante de Marketing. Compañero de facultad.',           TRUE,  'linkedin.com/in/tomasfernandez',   'Buenos Aires, Argentina', '+54-11-5555-2222', 70, 'tomas.jpg'),
('10000000-0000-0000-0000-000000000013', 'Especialista Marketing Digital', 'Leandro Gómez',
 'Especialista en Marketing Digital con experiencia en campañas de performance.', TRUE, 'linkedin.com/in/leandrogomez', 'Buenos Aires, Argentina', '+54-11-5555-3333', 85, 'leandro.jpg'),
-- Candidatos adicionales para match con oferta Marketing Digital
('10000000-0000-0000-0000-000000000014', 'Estudiante de Comunicación',    'Sofía Ramírez',
 'Estudiante de Comunicación Social con interés en marketing digital y análisis de datos. Experiencia básica en redes sociales y herramientas de analítica.', TRUE, 'linkedin.com/in/sofiaramirez', 'Buenos Aires, Argentina', '+54-11-5555-4444', 75, 'sofia.jpg'),
('10000000-0000-0000-0000-000000000015', 'Estudiante de Publicidad',      'Nicolás Herrera',
 'Estudiante de Publicidad con experiencia en gestión de redes sociales para emprendimientos. Busca insertarse en el mundo del marketing digital.', FALSE, 'linkedin.com/in/nicolasherrera', 'Córdoba, Argentina', '+54-351-555-6666', 60, 'nicolas.jpg'),
('10000000-0000-0000-0000-000000000016', 'Pasante de Marketing',          'Valentina Castro',
 'Recién graduada en Marketing con pasantía en agencia digital. Conocimientos en Google Analytics y campañas de Meta Ads.', TRUE, 'linkedin.com/in/valentinacastro', 'Buenos Aires, Argentina', '+54-11-5555-7777', 80, 'valentina.jpg')
ON CONFLICT (id) DO NOTHING;


-- ----- Reclutadores -----
INSERT INTO recruiter (id, company_id)
VALUES
('60000000-0000-0000-0000-000000000001', '50000000-0000-0000-0000-000000000001'),
('60000000-0000-0000-0000-000000000002', '50000000-0000-0000-0000-000000000002'),
('60000000-0000-0000-0000-000000000003', '50000000-0000-0000-0000-000000000003'),
-- Reclutadora TechNova (Demo Final)
('60000000-0000-0000-0000-000000000004', '50000000-0000-0000-0000-000000000004')
ON CONFLICT (id) DO NOTHING;


-- ==========================================================
-- 4. DEPENDIENTES NIVEL 2 (Sub-entidades del candidato)
-- ==========================================================

-- ----- Experiencia laboral -----
INSERT INTO work_experience (id, candidate_id, company, description, position, start_date, end_date, is_current)
VALUES
('30000000-0000-0000-0000-000000000001', '10000000-0000-0000-0000-000000000001',
 'Finanzas Globales S.A.',    'Mantenimiento de microservicios Java de misión crítica.',           'Desarrolladora Backend',    '2019-01-01', NULL,         TRUE),
('30000000-0000-0000-0000-000000000002', '10000000-0000-0000-0000-000000000002',
 'Estudio Web',               'Construcción de componentes interactivos con React.',               'Desarrollador Frontend',    '2020-05-01', NULL,         TRUE),
('30000000-0000-0000-0000-000000000003', '10000000-0000-0000-0000-000000000003',
 'Datos al Día',              'Creación de modelos predictivos usando Python.',                    'Científico de Datos',       '2018-08-01', '2023-01-01', FALSE),
('30000000-0000-0000-0000-000000000004', '10000000-0000-0000-0000-000000000004',
 'NubeSistemas',              'Migración de arquitectura legacy a AWS.',                           'Arquitecta Cloud',          '2016-03-01', NULL,         TRUE),
('30000000-0000-0000-0000-000000000005', '10000000-0000-0000-0000-000000000005',
 'Agencia Creativa',          'Liderazgo en diseño de producto usando Figma.',                     'Diseñador Senior',          '2021-01-01', NULL,         TRUE),
('30000000-0000-0000-0000-000000000006', '10000000-0000-0000-0000-000000000006',
 'Startup X',                 'Integración de interfaces React con APIs Java.',                    'Desarrolladora Full Stack', '2020-10-01', NULL,         TRUE),
('30000000-0000-0000-0000-000000000007', '10000000-0000-0000-0000-000000000007',
 'Laboratorio Universitario', 'Escritura de scripts básicos de procesamiento de datos en Python.', 'Pasante',                   '2022-06-01', '2022-09-01', FALSE),
('30000000-0000-0000-0000-000000000008', '10000000-0000-0000-0000-000000000008',
 'Banco Nacional',            'Administración y respaldo de bases de datos PostgreSQL.',           'DBA',                       '2015-02-01', NULL,         TRUE),
('30000000-0000-0000-0000-000000000009', '10000000-0000-0000-0000-000000000009',
 'Comercio Electrónico S.A.', 'Configuración de pipelines CI/CD y automatización.',               'Ingeniero DevOps',          '2019-11-01', NULL,         TRUE),
('30000000-0000-0000-0000-000000000010', '10000000-0000-0000-0000-000000000010',
 'Empresa Tech',              'Mentoría y liderazgo de un equipo de 15 ingenieros.',               'Líder Técnico',             '2014-07-01', NULL,         TRUE),
-- Experiencia Martina López (Demo Final)
('30000000-0000-0000-0000-000000000011', '10000000-0000-0000-0000-000000000011',
 'Agencia Nova Marketing',    'Gestión de campañas, gestión de redes sociales, elaboración de reportes, análisis de métricas, seguimiento de campañas.', 'Pasante', '2023-06-01', '2023-12-01', FALSE)
ON CONFLICT (id) DO NOTHING;


-- ----- Habilidades de candidatos (técnicas + blandas, 4-5 por candidato) -----
INSERT INTO candidate_skill (id, candidate_id, skill_id, consolidated_level, consolidated_score,
                             experience_range, created_at)
VALUES
-- Alicia: Java, Spring Boot, Docker + Leadership, Teamwork
('25000000-0000-0000-0000-000000000001', '10000000-0000-0000-0000-000000000001',
 '20000000-0000-0000-0000-000000000001', 'REFERENTE',         9.50, '10+ years',  '2023-01-01 10:00:00+00'),
('25000000-0000-0000-0000-000000000002', '10000000-0000-0000-0000-000000000001',
 '20000000-0000-0000-0000-000000000002', 'LIDER',             8.80, '7-10 years', '2023-01-01 10:00:00+00'),
('25000000-0000-0000-0000-000000000003', '10000000-0000-0000-0000-000000000001',
 '20000000-0000-0000-0000-000000000038', 'EJECUTOR_AUTONOMO', 7.00, '4-6 years',  '2023-01-01 10:00:00+00'),
('25000000-0000-0000-0000-000000000029', '10000000-0000-0000-0000-000000000001',
 '20000000-0000-0000-0000-000000000068', 'LIDER',             8.50, '7-10 years', '2023-01-01 10:00:00+00'),
('25000000-0000-0000-0000-000000000030', '10000000-0000-0000-0000-000000000001',
 '20000000-0000-0000-0000-000000000069', 'EJECUTOR_AUTONOMO', 7.80, '4-6 years',  '2023-01-01 10:00:00+00'),

-- Roberto: React, JavaScript, TypeScript + Communication, Creativity
('25000000-0000-0000-0000-000000000004', '10000000-0000-0000-0000-000000000002',
 '20000000-0000-0000-0000-000000000010', 'LIDER',             9.00, '4-6 years',  '2023-01-02 10:00:00+00'),
('25000000-0000-0000-0000-000000000005', '10000000-0000-0000-0000-000000000002',
 '20000000-0000-0000-0000-000000000009', 'REFERENTE',         9.20, '7-10 years', '2023-01-02 10:00:00+00'),
('25000000-0000-0000-0000-000000000006', '10000000-0000-0000-0000-000000000002',
 '20000000-0000-0000-0000-000000000008', 'EJECUTOR_AUTONOMO', 7.50, '1-3 years',  '2023-01-02 10:00:00+00'),
('25000000-0000-0000-0000-000000000031', '10000000-0000-0000-0000-000000000002',
 '20000000-0000-0000-0000-000000000067', 'EJECUTOR_AUTONOMO', 7.20, '4-6 years',  '2023-01-02 10:00:00+00'),
('25000000-0000-0000-0000-000000000032', '10000000-0000-0000-0000-000000000002',
 '20000000-0000-0000-0000-000000000082', 'COLABORADOR',       6.00, '1-3 years',  '2023-01-02 10:00:00+00'),

-- Carlos: Python, Machine Learning, Data Analysis + Mentoring, Problem Solving
('25000000-0000-0000-0000-000000000007', '10000000-0000-0000-0000-000000000003',
 '20000000-0000-0000-0000-000000000004', 'REFERENTE',         9.80, '10+ years',  '2023-01-03 10:00:00+00'),
('25000000-0000-0000-0000-000000000008', '10000000-0000-0000-0000-000000000003',
 '20000000-0000-0000-0000-000000000052', 'LIDER',             9.20, '7-10 years', '2023-01-03 10:00:00+00'),
('25000000-0000-0000-0000-000000000009', '10000000-0000-0000-0000-000000000003',
 '20000000-0000-0000-0000-000000000055', 'EJECUTOR_AUTONOMO', 8.00, '4-6 years',  '2023-01-03 10:00:00+00'),
('25000000-0000-0000-0000-000000000033', '10000000-0000-0000-0000-000000000003',
 '20000000-0000-0000-0000-000000000075', 'LIDER',             8.60, '7-10 years', '2023-01-03 10:00:00+00'),
('25000000-0000-0000-0000-000000000034', '10000000-0000-0000-0000-000000000003',
 '20000000-0000-0000-0000-000000000070', 'REFERENTE',         9.30, '10+ years',  '2023-01-03 10:00:00+00'),

-- Diana: AWS, Terraform, Kubernetes + Decision Making, Leadership
('25000000-0000-0000-0000-000000000010', '10000000-0000-0000-0000-000000000004',
 '20000000-0000-0000-0000-000000000040', 'REFERENTE',         9.90, '10+ years',  '2023-01-04 10:00:00+00'),
('25000000-0000-0000-0000-000000000011', '10000000-0000-0000-0000-000000000004',
 '20000000-0000-0000-0000-000000000043', 'LIDER',             8.50, '4-6 years',  '2023-01-04 10:00:00+00'),
('25000000-0000-0000-0000-000000000012', '10000000-0000-0000-0000-000000000004',
 '20000000-0000-0000-0000-000000000039', 'EJECUTOR_AUTONOMO', 7.80, '4-6 years',  '2023-01-04 10:00:00+00'),
('25000000-0000-0000-0000-000000000035', '10000000-0000-0000-0000-000000000004',
 '20000000-0000-0000-0000-000000000083', 'EJECUTOR_AUTONOMO', 7.50, '4-6 years',  '2023-01-04 10:00:00+00'),
('25000000-0000-0000-0000-000000000036', '10000000-0000-0000-0000-000000000004',
 '20000000-0000-0000-0000-000000000068', 'EJECUTOR_AUTONOMO', 7.00, '4-6 years',  '2023-01-04 10:00:00+00'),

-- Esteban: Figma, UI Design + Creativity, Communication
('25000000-0000-0000-0000-000000000013', '10000000-0000-0000-0000-000000000005',
 '20000000-0000-0000-0000-000000000058', 'EJECUTOR_AUTONOMO', 8.50, '1-3 years',  '2023-01-05 10:00:00+00'),
('25000000-0000-0000-0000-000000000014', '10000000-0000-0000-0000-000000000005',
 '20000000-0000-0000-0000-000000000059', 'EJECUTOR_AUTONOMO', 8.00, '1-3 years',  '2023-01-05 10:00:00+00'),
('25000000-0000-0000-0000-000000000037', '10000000-0000-0000-0000-000000000005',
 '20000000-0000-0000-0000-000000000082', 'EJECUTOR_AUTONOMO', 8.20, '1-3 years',  '2023-01-05 10:00:00+00'),
('25000000-0000-0000-0000-000000000038', '10000000-0000-0000-0000-000000000005',
 '20000000-0000-0000-0000-000000000067', 'COLABORADOR',       6.50, '1-3 years',  '2023-01-05 10:00:00+00'),

-- Flavia: React, Java, REST APIs + Teamwork, Adaptability
('25000000-0000-0000-0000-000000000015', '10000000-0000-0000-0000-000000000006',
 '20000000-0000-0000-0000-000000000010', 'EJECUTOR_AUTONOMO', 7.00, '1-3 years',  '2023-01-06 10:00:00+00'),
('25000000-0000-0000-0000-000000000016', '10000000-0000-0000-0000-000000000006',
 '20000000-0000-0000-0000-000000000001', 'EJECUTOR_AUTONOMO', 7.20, '1-3 years',  '2023-01-06 10:00:00+00'),
('25000000-0000-0000-0000-000000000017', '10000000-0000-0000-0000-000000000006',
 '20000000-0000-0000-0000-000000000036', 'COLABORADOR',       5.50, '<1 year',    '2023-01-06 10:00:00+00'),
('25000000-0000-0000-0000-000000000039', '10000000-0000-0000-0000-000000000006',
 '20000000-0000-0000-0000-000000000069', 'EJECUTOR_AUTONOMO', 7.50, '1-3 years',  '2023-01-06 10:00:00+00'),
('25000000-0000-0000-0000-000000000040', '10000000-0000-0000-0000-000000000006',
 '20000000-0000-0000-0000-000000000072', 'COLABORADOR',       6.00, '<1 year',    '2023-01-06 10:00:00+00'),

-- Guillermo: Python, JavaScript + Adaptability, Teamwork
('25000000-0000-0000-0000-000000000018', '10000000-0000-0000-0000-000000000007',
 '20000000-0000-0000-0000-000000000004', 'COLABORADOR',       4.50, '<1 year',    '2023-01-07 10:00:00+00'),
('25000000-0000-0000-0000-000000000019', '10000000-0000-0000-0000-000000000007',
 '20000000-0000-0000-0000-000000000009', 'COLABORADOR',       4.00, '<1 year',    '2023-01-07 10:00:00+00'),
('25000000-0000-0000-0000-000000000041', '10000000-0000-0000-0000-000000000007',
 '20000000-0000-0000-0000-000000000072', 'COLABORADOR',       5.00, '<1 year',    '2023-01-07 10:00:00+00'),
('25000000-0000-0000-0000-000000000042', '10000000-0000-0000-0000-000000000007',
 '20000000-0000-0000-0000-000000000069', 'COLABORADOR',       5.50, '<1 year',    '2023-01-07 10:00:00+00'),

-- Helena: PostgreSQL, SQL, Redis + Problem Solving, Time Management
('25000000-0000-0000-0000-000000000020', '10000000-0000-0000-0000-000000000008',
 '20000000-0000-0000-0000-000000000030', 'REFERENTE',         9.70, '10+ years',  '2023-01-08 10:00:00+00'),
('25000000-0000-0000-0000-000000000021', '10000000-0000-0000-0000-000000000008',
 '20000000-0000-0000-0000-000000000029', 'LIDER',             9.00, '7-10 years', '2023-01-08 10:00:00+00'),
('25000000-0000-0000-0000-000000000022', '10000000-0000-0000-0000-000000000008',
 '20000000-0000-0000-0000-000000000033', 'EJECUTOR_AUTONOMO', 7.50, '4-6 years',  '2023-01-08 10:00:00+00'),
('25000000-0000-0000-0000-000000000043', '10000000-0000-0000-0000-000000000008',
 '20000000-0000-0000-0000-000000000070', 'LIDER',             8.80, '7-10 years', '2023-01-08 10:00:00+00'),
('25000000-0000-0000-0000-000000000044', '10000000-0000-0000-0000-000000000008',
 '20000000-0000-0000-0000-000000000073', 'EJECUTOR_AUTONOMO', 7.60, '4-6 years',  '2023-01-08 10:00:00+00'),

-- Ignacio: DevOps, Docker, CI/CD + Leadership, Critical Thinking
('25000000-0000-0000-0000-000000000023', '10000000-0000-0000-0000-000000000009',
 '20000000-0000-0000-0000-000000000064', 'LIDER',             8.80, '7-10 years', '2023-01-09 10:00:00+00'),
('25000000-0000-0000-0000-000000000024', '10000000-0000-0000-0000-000000000009',
 '20000000-0000-0000-0000-000000000038', 'REFERENTE',         9.00, '7-10 years', '2023-01-09 10:00:00+00'),
('25000000-0000-0000-0000-000000000025', '10000000-0000-0000-0000-000000000009',
 '20000000-0000-0000-0000-000000000044', 'LIDER',             8.50, '4-6 years',  '2023-01-09 10:00:00+00'),
('25000000-0000-0000-0000-000000000045', '10000000-0000-0000-0000-000000000009',
 '20000000-0000-0000-0000-000000000068', 'EJECUTOR_AUTONOMO', 7.50, '4-6 years',  '2023-01-09 10:00:00+00'),
('25000000-0000-0000-0000-000000000046', '10000000-0000-0000-0000-000000000009',
 '20000000-0000-0000-0000-000000000071', 'EJECUTOR_AUTONOMO', 7.80, '4-6 years',  '2023-01-09 10:00:00+00'),

-- Julieta: Leadership, Scrum, Java + Mentoring, Stakeholder Management
('25000000-0000-0000-0000-000000000026', '10000000-0000-0000-0000-000000000010',
 '20000000-0000-0000-0000-000000000068', 'REFERENTE',         9.60, '10+ years',  '2023-01-10 10:00:00+00'),
('25000000-0000-0000-0000-000000000027', '10000000-0000-0000-0000-000000000010',
 '20000000-0000-0000-0000-000000000065', 'LIDER',             8.70, '7-10 years', '2023-01-10 10:00:00+00'),
('25000000-0000-0000-0000-000000000028', '10000000-0000-0000-0000-000000000010',
 '20000000-0000-0000-0000-000000000001', 'LIDER',             8.50, '7-10 years', '2023-01-10 10:00:00+00'),
('25000000-0000-0000-0000-000000000047', '10000000-0000-0000-0000-000000000010',
 '20000000-0000-0000-0000-000000000075', 'REFERENTE',         9.40, '10+ years',  '2023-01-10 10:00:00+00'),
('25000000-0000-0000-0000-000000000048', '10000000-0000-0000-0000-000000000010',
 '20000000-0000-0000-0000-000000000076', 'LIDER',             8.90, '7-10 years', '2023-01-10 10:00:00+00'),

-- Martina López: Habilidades de Marketing Digital (Demo Final)
('25000000-0000-0000-0000-000000000049', '10000000-0000-0000-0000-000000000011',
 '20000000-0000-0000-0000-000000000085', 'COLABORADOR',       6.50, '<1 year',    '2023-04-01 10:00:00+00'),  -- Marketing Digital
('25000000-0000-0000-0000-000000000050', '10000000-0000-0000-0000-000000000011',
 '20000000-0000-0000-0000-000000000086', 'COLABORADOR',       6.00, '<1 year',    '2023-04-01 10:00:00+00'),  -- Gestión de Redes Sociales
('25000000-0000-0000-0000-000000000051', '10000000-0000-0000-0000-000000000011',
 '20000000-0000-0000-0000-000000000087', 'COLABORADOR',       5.80, '<1 year',    '2023-04-01 10:00:00+00'),  -- Meta Ads
('25000000-0000-0000-0000-000000000052', '10000000-0000-0000-0000-000000000011',
 '20000000-0000-0000-0000-000000000088', 'COLABORADOR',       6.20, '<1 year',    '2023-04-01 10:00:00+00'),  -- Google Analytics
('25000000-0000-0000-0000-000000000053', '10000000-0000-0000-0000-000000000011',
 '20000000-0000-0000-0000-000000000089', 'COLABORADOR',       6.00, '<1 year',    '2023-04-01 10:00:00+00'),  -- Análisis de Métricas
('25000000-0000-0000-0000-000000000054', '10000000-0000-0000-0000-000000000011',
 '20000000-0000-0000-0000-000000000093', 'COLABORADOR',       5.50, '<1 year',    '2023-04-01 10:00:00+00'),  -- Trabajo en Equipo
('25000000-0000-0000-0000-000000000055', '10000000-0000-0000-0000-000000000011',
 '20000000-0000-0000-0000-000000000090', 'COLABORADOR',       5.00, '<1 year',    '2023-04-01 10:00:00+00'),  -- Gestión de Campañas Digitales

-- Leandro Gómez: Habilidades de Marketing Digital (Demo Final - validador sugerido)
('25000000-0000-0000-0000-000000000056', '10000000-0000-0000-0000-000000000013',
 '20000000-0000-0000-0000-000000000085', 'LIDER',             8.50, '4-6 years',  '2023-04-01 10:00:00+00'),  -- Marketing Digital
('25000000-0000-0000-0000-000000000057', '10000000-0000-0000-0000-000000000013',
 '20000000-0000-0000-0000-000000000090', 'EJECUTOR_AUTONOMO', 7.80, '1-3 years',  '2023-04-01 10:00:00+00'),  -- Gestión de Campañas Digitales
('25000000-0000-0000-0000-000000000058', '10000000-0000-0000-0000-000000000013',
 '20000000-0000-0000-0000-000000000091', 'EJECUTOR_AUTONOMO', 7.50, '1-3 years',  '2023-04-01 10:00:00+00'),  -- Google Ads
('25000000-0000-0000-0000-000000000059', '10000000-0000-0000-0000-000000000013',
 '20000000-0000-0000-0000-000000000092', 'EJECUTOR_AUTONOMO', 7.00, '1-3 years',  '2023-04-01 10:00:00+00'),  -- SEO

-- Tomás Fernández: Habilidades (Demo Final - validador)
('25000000-0000-0000-0000-000000000060', '10000000-0000-0000-0000-000000000012',
 '20000000-0000-0000-0000-000000000085', 'COLABORADOR',       5.50, '<1 year',    '2023-04-01 10:00:00+00'),  -- Marketing Digital
('25000000-0000-0000-0000-000000000061', '10000000-0000-0000-0000-000000000012',
 '20000000-0000-0000-0000-000000000093', 'COLABORADOR',       6.00, '<1 year',    '2023-04-01 10:00:00+00'),  -- Trabajo en Equipo

-- Sofía Ramírez: tiene 5 de 9 skills de la oferta (~70% match)
-- Tiene: Marketing Digital, Google Analytics, Análisis de Métricas, Trabajo en Equipo, Gestión de Campañas Digitales
-- No tiene: Gestión de Redes Sociales, Meta Ads, Google Ads, SEO
('25000000-0000-0000-0000-000000000062', '10000000-0000-0000-0000-000000000014',
 '20000000-0000-0000-0000-000000000085', 'COLABORADOR',       5.80, '<1 year',    '2023-04-05 10:00:00+00'),  -- Marketing Digital
('25000000-0000-0000-0000-000000000063', '10000000-0000-0000-0000-000000000014',
 '20000000-0000-0000-0000-000000000088', 'COLABORADOR',       5.50, '<1 year',    '2023-04-05 10:00:00+00'),  -- Google Analytics
('25000000-0000-0000-0000-000000000064', '10000000-0000-0000-0000-000000000014',
 '20000000-0000-0000-0000-000000000089', 'COLABORADOR',       5.20, '<1 year',    '2023-04-05 10:00:00+00'),  -- Análisis de Métricas
('25000000-0000-0000-0000-000000000065', '10000000-0000-0000-0000-000000000014',
 '20000000-0000-0000-0000-000000000093', 'COLABORADOR',       6.00, '<1 year',    '2023-04-05 10:00:00+00'),  -- Trabajo en Equipo
('25000000-0000-0000-0000-000000000066', '10000000-0000-0000-0000-000000000014',
 '20000000-0000-0000-0000-000000000090', 'COLABORADOR',       4.80, '<1 year',    '2023-04-05 10:00:00+00'),  -- Gestión de Campañas Digitales

-- Nicolás Herrera: tiene 3 de 9 skills de la oferta (~50% match)
-- Tiene: Marketing Digital, Gestión de Redes Sociales, Trabajo en Equipo
-- No tiene: Meta Ads, Google Analytics, Análisis de Métricas, Gestión de Campañas Digitales, Google Ads, SEO
('25000000-0000-0000-0000-000000000067', '10000000-0000-0000-0000-000000000015',
 '20000000-0000-0000-0000-000000000085', 'COLABORADOR',       4.50, '<1 year',    '2023-04-06 10:00:00+00'),  -- Marketing Digital
('25000000-0000-0000-0000-000000000068', '10000000-0000-0000-0000-000000000015',
 '20000000-0000-0000-0000-000000000086', 'COLABORADOR',       5.00, '<1 year',    '2023-04-06 10:00:00+00'),  -- Gestión de Redes Sociales
('25000000-0000-0000-0000-000000000069', '10000000-0000-0000-0000-000000000015',
 '20000000-0000-0000-0000-000000000093', 'COLABORADOR',       5.50, '<1 year',    '2023-04-06 10:00:00+00'),  -- Trabajo en Equipo

-- Valentina Castro: tiene 4 de 9 skills de la oferta (~60% match)
-- Tiene: Marketing Digital, Meta Ads, Google Analytics, Gestión de Campañas Digitales
-- No tiene: Gestión de Redes Sociales, Análisis de Métricas, Trabajo en Equipo, Google Ads, SEO
('25000000-0000-0000-0000-000000000070', '10000000-0000-0000-0000-000000000016',
 '20000000-0000-0000-0000-000000000085', 'COLABORADOR',       6.00, '<1 year',    '2023-04-07 10:00:00+00'),  -- Marketing Digital
('25000000-0000-0000-0000-000000000071', '10000000-0000-0000-0000-000000000016',
 '20000000-0000-0000-0000-000000000087', 'COLABORADOR',       5.50, '<1 year',    '2023-04-07 10:00:00+00'),  -- Meta Ads
('25000000-0000-0000-0000-000000000072', '10000000-0000-0000-0000-000000000016',
 '20000000-0000-0000-0000-000000000088', 'COLABORADOR',       5.80, '<1 year',    '2023-04-07 10:00:00+00'),  -- Google Analytics
('25000000-0000-0000-0000-000000000073', '10000000-0000-0000-0000-000000000016',
 '20000000-0000-0000-0000-000000000090', 'COLABORADOR',       5.00, '<1 year',    '2023-04-07 10:00:00+00')   -- Gestión de Campañas Digitales
ON CONFLICT (id) DO NOTHING;


-- ----- Proyectos -----
INSERT INTO project (id, candidate_id, title, description, link)
VALUES
('40000000-0000-0000-0000-000000000001', '10000000-0000-0000-0000-000000000001',
 'Pasarela de Pagos',             'Integración de la API de Stripe mediante Spring Boot.',                'github.com/alicia/pagos'),
('40000000-0000-0000-0000-000000000002', '10000000-0000-0000-0000-000000000002',
 'Librería de Componentes',       'Librería open-source de componentes React con Storybook.',             'github.com/roberto/componentes'),
('40000000-0000-0000-0000-000000000003', '10000000-0000-0000-0000-000000000003',
 'Predictor de Mercado',          'Modelo predictivo de machine learning para tendencias bursátiles.',    'github.com/carlos/mercado'),
('40000000-0000-0000-0000-000000000004', '10000000-0000-0000-0000-000000000004',
 'Kit de Migración Cloud',        'Módulos Terraform para setup de landing zone en AWS.',                 'github.com/diana/kit-cloud'),
('40000000-0000-0000-0000-000000000005', '10000000-0000-0000-0000-000000000009',
 'Framework de Pipelines CI/CD',  'Workflows reutilizables de GitHub Actions para microservicios.',       'github.com/ignacio/cicd-framework'),
('40000000-0000-0000-0000-000000000006', '10000000-0000-0000-0000-000000000010',
 'Manual de Ingeniería',          'Sitio de documentación interna sobre mejores prácticas de ingeniería.','github.com/julieta/manual')
ON CONFLICT (id) DO NOTHING;


-- ----- Reputación de validadores -----
INSERT INTO validator_reputation (user_id, identity_verified, platform_years, reputation_level,
                                  reputation_score, seniority, success_rate, total_validations)
VALUES
('10000000-0000-0000-0000-000000000003', TRUE,  5, 'ORO',     4.80, 'SENIOR', 95, 20),
('10000000-0000-0000-0000-000000000001', TRUE,  3, 'PLATA',   3.50, 'SENIOR', 88,  8),
('10000000-0000-0000-0000-000000000010', TRUE,  8, 'PLATINO', 4.95, 'SENIOR', 98, 50),
('10000000-0000-0000-0000-000000000004', TRUE,  4, 'ORO',     4.20, 'SENIOR', 92, 15),
('10000000-0000-0000-0000-000000000008', TRUE,  6, 'ORO',     4.50, 'SENIOR', 90, 18),
-- Martina López - validadora (Demo Final)
('10000000-0000-0000-0000-000000000011', TRUE,  1, 'BRONCE',  2.00, 'JUNIOR', 0, 0),
-- Leandro Gómez - validador sugerido (Demo Final)
('10000000-0000-0000-0000-000000000013', TRUE,  3, 'PLATA',   3.80, 'SEMI_SENIOR', 90, 10)
ON CONFLICT (user_id) DO NOTHING;


-- ==========================================================
-- 5. OFERTAS LABORALES Y ECOSISTEMA
-- ==========================================================

INSERT INTO job_offer (id, company_id, recruiter_id, created_at, title, description, benefits,
                       location, modality, salary_min, salary_max, seniority, status)
VALUES
('70000000-0000-0000-0000-000000000001', '50000000-0000-0000-0000-000000000001',
 '60000000-0000-0000-0000-000000000001', '2023-02-01 10:00:00+00',
 'Desarrollador/a Java Senior',
 'Buscamos un experto en Java para liderar nuestra arquitectura backend.',
 'Seguro médico, plan de retiro, bonos por desempeño.',
 'Madrid (Híbrido)', 'HYBRID', 50000.00, 70000.00, 'SENIOR', 'PUBLISHED'),

('70000000-0000-0000-0000-000000000002', '50000000-0000-0000-0000-000000000002',
 '60000000-0000-0000-0000-000000000002', '2023-02-15 10:00:00+00',
 'Científico/a de Datos Semi Senior',
 'Sumate a nuestro equipo de analítica predictiva.',
 '100% remoto, 25 días de vacaciones.',
 'Remoto', 'REMOTE', 40000.00, 60000.00, 'SEMI_SENIOR', 'PUBLISHED'),

('70000000-0000-0000-0000-000000000003', '50000000-0000-0000-0000-000000000001',
 '60000000-0000-0000-0000-000000000001', '2023-03-01 10:00:00+00',
 'Desarrollador/a Frontend Junior',
 'Gran oportunidad para desarrolladores junior apasionados por React.',
 'Programa de mentoría, horarios flexibles.',
 'Madrid (Presencial)', 'ONSITE', 25000.00, 35000.00, 'JUNIOR', 'PUBLISHED'),

('70000000-0000-0000-0000-000000000004', '50000000-0000-0000-0000-000000000002',
 '60000000-0000-0000-0000-000000000002', '2023-03-10 10:00:00+00',
 'Líder DevOps',
 'Liderá nuestra transformación de infraestructura y CI/CD.',
 'Stock options, remote-first, retiro anual del equipo.',
 'Remoto', 'REMOTE', 70000.00, 95000.00, 'LEAD', 'DRAFT'),

('70000000-0000-0000-0000-000000000005', '50000000-0000-0000-0000-000000000003',
 '60000000-0000-0000-0000-000000000003', '2023-01-20 10:00:00+00',
 'Desarrollador/a Full Stack',
 'Construí y mantené nuestra plataforma de e-commerce.',
 'Descuentos para empleados, plan de salud.',
 'Lima (Híbrido)', 'HYBRID', 35000.00, 50000.00, 'SEMI_SENIOR', 'CLOSED'),

-- Oferta TechNova - Analista Junior de Marketing Digital (Demo Final)
('70000000-0000-0000-0000-000000000006', '50000000-0000-0000-0000-000000000004',
 '60000000-0000-0000-0000-000000000004', '2023-04-15 10:00:00+00',
 'Analista Junior de Marketing Digital',
 'Buscamos una persona con ganas de desarrollarse en Marketing Digital. Participará en campañas digitales, optimización de anuncios, análisis de métricas y elaboración de reportes. Valoramos personas con iniciativa, pensamiento analítico y trabajo colaborativo.',
 'Modalidad híbrida (3 días remoto, 2 presencial), capacitación continua, obra social.',
 'Buenos Aires', 'HYBRID', 2100.00, 2500.00, 'JUNIOR', 'PUBLISHED'),

-- Oferta DigiCommerce - Community Manager Junior (Demo Final - match ~62% Martina)
('70000000-0000-0000-0000-000000000007', '50000000-0000-0000-0000-000000000003',
 '60000000-0000-0000-0000-000000000003', '2023-04-20 10:00:00+00',
 'Community Manager Junior',
 'Buscamos a alguien creativo para gestionar nuestras redes sociales, crear contenido y analizar el impacto de las publicaciones. Se requiere experiencia en SEO y Google Ads para potenciar el alcance orgánico y pago.',
 'Horarios flexibles, descuentos en productos, capacitación.',
 'Buenos Aires', 'REMOTE', 1800.00, 2200.00, 'JUNIOR', 'PUBLISHED'),

-- Oferta Innovación Tecnológica - Asistente de E-commerce (Demo Final - match ~45% Martina)
('70000000-0000-0000-0000-000000000008', '50000000-0000-0000-0000-000000000001',
 '60000000-0000-0000-0000-000000000001', '2023-04-22 10:00:00+00',
 'Asistente de E-commerce y Marketing',
 'Sumate a nuestro equipo de comercio electrónico. Necesitamos alguien que combine conocimientos de marketing digital con habilidades técnicas para optimizar nuestra tienda online y campañas de adquisición.',
 'Seguro médico, bono semestral, trabajo remoto.',
 'Buenos Aires (Híbrido)', 'HYBRID', 1600.00, 2000.00, 'JUNIOR', 'PUBLISHED')
ON CONFLICT (id) DO NOTHING;


-- ----- Habilidades requeridas por oferta -----
INSERT INTO offer_skill (offer_id, skill_id, requirement)
VALUES
('70000000-0000-0000-0000-000000000001', '20000000-0000-0000-0000-000000000001', 'REQUIRED'),
('70000000-0000-0000-0000-000000000001', '20000000-0000-0000-0000-000000000002', 'REQUIRED'),
('70000000-0000-0000-0000-000000000001', '20000000-0000-0000-0000-000000000038', 'DESIRABLE'),
('70000000-0000-0000-0000-000000000001', '20000000-0000-0000-0000-000000000037', 'DESIRABLE'),
('70000000-0000-0000-0000-000000000002', '20000000-0000-0000-0000-000000000004', 'REQUIRED'),
('70000000-0000-0000-0000-000000000002', '20000000-0000-0000-0000-000000000052', 'REQUIRED'),
('70000000-0000-0000-0000-000000000002', '20000000-0000-0000-0000-000000000055', 'DESIRABLE'),
('70000000-0000-0000-0000-000000000003', '20000000-0000-0000-0000-000000000010', 'REQUIRED'),
('70000000-0000-0000-0000-000000000003', '20000000-0000-0000-0000-000000000009', 'REQUIRED'),
('70000000-0000-0000-0000-000000000003', '20000000-0000-0000-0000-000000000015', 'DESIRABLE'),
('70000000-0000-0000-0000-000000000003', '20000000-0000-0000-0000-000000000014', 'DESIRABLE'),
('70000000-0000-0000-0000-000000000004', '20000000-0000-0000-0000-000000000064', 'REQUIRED'),
('70000000-0000-0000-0000-000000000004', '20000000-0000-0000-0000-000000000038', 'REQUIRED'),
('70000000-0000-0000-0000-000000000004', '20000000-0000-0000-0000-000000000039', 'REQUIRED'),
('70000000-0000-0000-0000-000000000004', '20000000-0000-0000-0000-000000000040', 'DESIRABLE'),
('70000000-0000-0000-0000-000000000004', '20000000-0000-0000-0000-000000000043', 'DESIRABLE'),
('70000000-0000-0000-0000-000000000005', '20000000-0000-0000-0000-000000000010', 'REQUIRED'),
('70000000-0000-0000-0000-000000000005', '20000000-0000-0000-0000-000000000001', 'REQUIRED'),
('70000000-0000-0000-0000-000000000005', '20000000-0000-0000-0000-000000000036', 'DESIRABLE'),
-- Habilidades requeridas para oferta Marketing Digital TechNova (Demo Final)
('70000000-0000-0000-0000-000000000006', '20000000-0000-0000-0000-000000000085', 'REQUIRED'),  -- Marketing Digital
('70000000-0000-0000-0000-000000000006', '20000000-0000-0000-0000-000000000086', 'REQUIRED'),  -- Gestión de Redes Sociales
('70000000-0000-0000-0000-000000000006', '20000000-0000-0000-0000-000000000087', 'REQUIRED'),  -- Meta Ads
('70000000-0000-0000-0000-000000000006', '20000000-0000-0000-0000-000000000088', 'REQUIRED'),  -- Google Analytics
('70000000-0000-0000-0000-000000000006', '20000000-0000-0000-0000-000000000089', 'REQUIRED'),  -- Análisis de Métricas
('70000000-0000-0000-0000-000000000006', '20000000-0000-0000-0000-000000000093', 'REQUIRED'),  -- Trabajo en Equipo
('70000000-0000-0000-0000-000000000006', '20000000-0000-0000-0000-000000000090', 'DESIRABLE'), -- Gestión de Campañas Digitales
('70000000-0000-0000-0000-000000000006', '20000000-0000-0000-0000-000000000091', 'DESIRABLE'), -- Google Ads
('70000000-0000-0000-0000-000000000006', '20000000-0000-0000-0000-000000000092', 'DESIRABLE'), -- SEO
-- Habilidades requeridas para Community Manager Junior (DigiCommerce)
-- Martina tiene: Gestión de Redes Sociales, Marketing Digital, Meta Ads, Análisis de Métricas
-- Martina NO tiene: SEO, Google Ads, HTML, CSS
('70000000-0000-0000-0000-000000000007', '20000000-0000-0000-0000-000000000086', 'REQUIRED'),  -- Gestión de Redes Sociales (tiene)
('70000000-0000-0000-0000-000000000007', '20000000-0000-0000-0000-000000000085', 'REQUIRED'),  -- Marketing Digital (tiene)
('70000000-0000-0000-0000-000000000007', '20000000-0000-0000-0000-000000000092', 'REQUIRED'),  -- SEO (NO tiene)
('70000000-0000-0000-0000-000000000007', '20000000-0000-0000-0000-000000000091', 'REQUIRED'),  -- Google Ads (NO tiene)
('70000000-0000-0000-0000-000000000007', '20000000-0000-0000-0000-000000000087', 'DESIRABLE'), -- Meta Ads (tiene)
('70000000-0000-0000-0000-000000000007', '20000000-0000-0000-0000-000000000089', 'DESIRABLE'), -- Análisis de Métricas (tiene)
('70000000-0000-0000-0000-000000000007', '20000000-0000-0000-0000-000000000014', 'DESIRABLE'), -- HTML (NO tiene)
-- Habilidades requeridas para Asistente de E-commerce (Innovación Tecnológica)
-- Martina tiene: Marketing Digital, Google Analytics
-- Martina NO tiene: HTML, CSS, SEO, Google Ads, React
('70000000-0000-0000-0000-000000000008', '20000000-0000-0000-0000-000000000085', 'REQUIRED'),  -- Marketing Digital (tiene)
('70000000-0000-0000-0000-000000000008', '20000000-0000-0000-0000-000000000014', 'REQUIRED'),  -- HTML (NO tiene)
('70000000-0000-0000-0000-000000000008', '20000000-0000-0000-0000-000000000015', 'REQUIRED'),  -- CSS (NO tiene)
('70000000-0000-0000-0000-000000000008', '20000000-0000-0000-0000-000000000092', 'REQUIRED'),  -- SEO (NO tiene)
('70000000-0000-0000-0000-000000000008', '20000000-0000-0000-0000-000000000091', 'REQUIRED'),  -- Google Ads (NO tiene)
('70000000-0000-0000-0000-000000000008', '20000000-0000-0000-0000-000000000088', 'DESIRABLE'), -- Google Analytics (tiene)
('70000000-0000-0000-0000-000000000008', '20000000-0000-0000-0000-000000000010', 'DESIRABLE')  -- React (NO tiene)
ON CONFLICT (offer_id, skill_id) DO NOTHING;


-- ----- Matches (los tres estados posibles) -----
INSERT INTO match (id, candidate_id, offer_id, created_at, match_score, profile_revealed, revealed_at, status)
VALUES
('80000000-0000-0000-0000-000000000001', '10000000-0000-0000-0000-000000000001',
 '70000000-0000-0000-0000-000000000001', '2023-02-05 10:00:00+00', 95, FALSE, NULL, 'SUGGESTED'),
('80000000-0000-0000-0000-000000000002', '10000000-0000-0000-0000-000000000003',
 '70000000-0000-0000-0000-000000000002', '2023-02-20 10:00:00+00', 92, FALSE, NULL, 'INTERESTED'),
('80000000-0000-0000-0000-000000000003', '10000000-0000-0000-0000-000000000002',
 '70000000-0000-0000-0000-000000000003', '2023-03-05 10:00:00+00', 88, FALSE, NULL, 'SUGGESTED'),
('80000000-0000-0000-0000-000000000004', '10000000-0000-0000-0000-000000000009',
 '70000000-0000-0000-0000-000000000004', '2023-03-12 10:00:00+00', 85, FALSE, NULL, 'NOT_INTERESTED'),
('80000000-0000-0000-0000-000000000005', '10000000-0000-0000-0000-000000000006',
 '70000000-0000-0000-0000-000000000005', '2023-02-01 10:00:00+00', 78, TRUE,  '2023-02-10 14:00:00+00', 'INTERESTED'),
-- Match Martina con oferta TechNova (Demo Final) - 78% coincidencia
('80000000-0000-0000-0000-000000000006', '10000000-0000-0000-0000-000000000011',
 '70000000-0000-0000-0000-000000000006', '2023-04-16 10:00:00+00', 78, FALSE, NULL, 'SUGGESTED'),
-- Match Martina con Community Manager Junior (DigiCommerce) - 62% coincidencia
('80000000-0000-0000-0000-000000000007', '10000000-0000-0000-0000-000000000011',
 '70000000-0000-0000-0000-000000000007', '2023-04-21 10:00:00+00', 62, FALSE, NULL, 'SUGGESTED'),
-- Match Martina con Asistente de E-commerce (Innovación Tecnológica) - 45% coincidencia
('80000000-0000-0000-0000-000000000008', '10000000-0000-0000-0000-000000000011',
 '70000000-0000-0000-0000-000000000008', '2023-04-23 10:00:00+00', 45, FALSE, NULL, 'SUGGESTED'),
-- Matches de candidatos adicionales con Analista Junior de Marketing Digital (TechNova)
-- Sofía Ramírez - 70% coincidencia
('80000000-0000-0000-0000-000000000009', '10000000-0000-0000-0000-000000000014',
 '70000000-0000-0000-0000-000000000006', '2023-04-17 10:00:00+00', 70, FALSE, NULL, 'SUGGESTED'),
-- Nicolás Herrera - 50% coincidencia
('80000000-0000-0000-0000-000000000010', '10000000-0000-0000-0000-000000000015',
 '70000000-0000-0000-0000-000000000006', '2023-04-18 10:00:00+00', 50, FALSE, NULL, 'SUGGESTED'),
-- Valentina Castro - 60% coincidencia
('80000000-0000-0000-0000-000000000011', '10000000-0000-0000-0000-000000000016',
 '70000000-0000-0000-0000-000000000006', '2023-04-19 10:00:00+00', 60, FALSE, NULL, 'SUGGESTED')
ON CONFLICT (id) DO NOTHING;


-- ==========================================================
-- 6. MENSAJERÍA ANÓNIMA
-- ==========================================================

INSERT INTO anonymous_thread (id, candidate_id, offer_id, anonymous_code, category, created_at, status)
VALUES
('90000000-0000-0000-0000-000000000001', '10000000-0000-0000-0000-000000000001',
 '70000000-0000-0000-0000-000000000001', 'HILO-001', 'MODALITY', '2023-02-06 10:00:00+00', 'PENDING'),
('90000000-0000-0000-0000-000000000002', '10000000-0000-0000-0000-000000000003',
 '70000000-0000-0000-0000-000000000002', 'HILO-002', 'SALARY',   '2023-02-22 10:00:00+00', 'RESPONDED'),
('90000000-0000-0000-0000-000000000003', '10000000-0000-0000-0000-000000000002',
 '70000000-0000-0000-0000-000000000003', 'HILO-003', 'CULTURE',  '2023-03-06 10:00:00+00', 'PENDING')
ON CONFLICT (id) DO NOTHING;

INSERT INTO anonymous_message (id, thread_id, content, created_at, sender_type)
VALUES
('A0000000-0000-0000-0000-000000000001', '90000000-0000-0000-0000-000000000001',
 '¿El esquema híbrido es flexible o hay días fijos de oficina?',
 '2023-02-06 10:05:00+00', 'CANDIDATE'),
('A0000000-0000-0000-0000-000000000002', '90000000-0000-0000-0000-000000000002',
 '¿Cuál es el rango salarial real para candidatos 100% remotos?',
 '2023-02-22 10:10:00+00', 'CANDIDATE'),
('A0000000-0000-0000-0000-000000000003', '90000000-0000-0000-0000-000000000002',
 'El rango es de $40k-$60k dependiendo de la experiencia. También ofrecemos bonos por rendimiento.',
 '2023-02-22 14:30:00+00', 'RECRUITER'),
('A0000000-0000-0000-0000-000000000004', '90000000-0000-0000-0000-000000000003',
 '¿Cómo es la cultura del equipo en el día a día? ¿Es común el pair programming?',
 '2023-03-06 11:00:00+00', 'CANDIDATE')
ON CONFLICT (id) DO NOTHING;


-- ==========================================================
-- 7. SISTEMA DE VALIDACIÓN ENTRE PARES
-- ==========================================================

-- ----- Solicitudes de validación (incluye validaciones cruzadas entre candidatos) -----
INSERT INTO validation_request (id, requester_id, skill_id, validator_id, created_at, message,
                                relation_type, status)
VALUES
-- Guillermo le pide a Carlos que valide su Python
('B0000000-0000-0000-0000-000000000001', '10000000-0000-0000-0000-000000000007',
 '20000000-0000-0000-0000-000000000004', '10000000-0000-0000-0000-000000000003',
 '2023-03-01 10:00:00+00',
 'Hola Carlos, ¿podrías validar mi trabajo reciente procesando datos con Python?',
 'COWORKER', 'COMPLETED'),

-- Esteban le pide a Roberto que valide su React
('B0000000-0000-0000-0000-000000000002', '10000000-0000-0000-0000-000000000005',
 '20000000-0000-0000-0000-000000000010', '10000000-0000-0000-0000-000000000002',
 '2023-03-10 10:00:00+00',
 'Hola Roberto, trabajamos juntos en el proyecto de UI, ¿podrías validar mis habilidades en React?',
 'CLASSMATE', 'PENDING'),

-- Flavia le pide a Alicia que valide su Java
('B0000000-0000-0000-0000-000000000003', '10000000-0000-0000-0000-000000000006',
 '20000000-0000-0000-0000-000000000001', '10000000-0000-0000-0000-000000000001',
 '2023-03-15 10:00:00+00',
 'Hola Alicia, fuimos compañeras de equipo, ¿podrías validar mis skills en Java?',
 'TEAMMATE', 'COMPLETED'),

-- Diana le pide a Ignacio que valide su DevOps (validación cruzada técnica)
('B0000000-0000-0000-0000-000000000004', '10000000-0000-0000-0000-000000000004',
 '20000000-0000-0000-0000-000000000064', '10000000-0000-0000-0000-000000000009',
 '2023-03-18 10:00:00+00',
 'Hola Ignacio, colaboramos en la migración cloud. ¿Podrías validar mi conocimiento en DevOps?',
 'COWORKER', 'COMPLETED'),

-- Ignacio le pide a Diana que valide su AWS (validación cruzada inversa)
('B0000000-0000-0000-0000-000000000005', '10000000-0000-0000-0000-000000000009',
 '20000000-0000-0000-0000-000000000040', '10000000-0000-0000-0000-000000000004',
 '2023-03-19 10:00:00+00',
 'Hola Diana, vos sos la referente en AWS del equipo. ¿Podrías validar mi nivel?',
 'COWORKER', 'COMPLETED'),

-- Roberto le pide a Guillermo que valide su Teamwork (validación blanda cruzada)
('B0000000-0000-0000-0000-000000000006', '10000000-0000-0000-0000-000000000002',
 '20000000-0000-0000-0000-000000000069', '10000000-0000-0000-0000-000000000007',
 '2023-03-20 10:00:00+00',
 'Hola Guillermo, compartimos equipo en el proyecto. ¿Podrías validar mi trabajo en equipo?',
 'TEAMMATE', 'COMPLETED'),

-- Julieta le pide a Carlos que valide su Mentoring (validación blanda cruzada)
('B0000000-0000-0000-0000-000000000007', '10000000-0000-0000-0000-000000000010',
 '20000000-0000-0000-0000-000000000075', '10000000-0000-0000-0000-000000000003',
 '2023-03-22 10:00:00+00',
 'Hola Carlos, fui tu mentora durante el onboarding. ¿Podrías validar mis habilidades de mentoría?',
 'TECHLEAD', 'COMPLETED'),

-- Helena le pide a Alicia que valide su Problem Solving (validación blanda cruzada)
('B0000000-0000-0000-0000-000000000008', '10000000-0000-0000-0000-000000000008',
 '20000000-0000-0000-0000-000000000070', '10000000-0000-0000-0000-000000000001',
 '2023-03-25 10:00:00+00',
 'Hola Alicia, resolvimos juntas varios incidentes críticos. ¿Podrías validar mi resolución de problemas?',
 'COWORKER', 'COMPLETED'),

-- ========== Validaciones Demo Final ==========

-- Tomás le pide a Martina que valide su Trabajo en Equipo (PENDIENTE - Demo Final)
('B0000000-0000-0000-0000-000000000009', '10000000-0000-0000-0000-000000000012',
 '20000000-0000-0000-0000-000000000093', '10000000-0000-0000-0000-000000000011',
 '2023-04-20 10:00:00+00',
 'Hola Martina, trabajamos juntos en varios proyectos de la facultad. ¿Podrías validar mi habilidad de trabajo en equipo?',
 'CLASSMATE', 'PENDING'),

-- Solicitudes de validación completadas para las habilidades de Martina (5 habilidades ya validadas)
('B0000000-0000-0000-0000-000000000010', '10000000-0000-0000-0000-000000000011',
 '20000000-0000-0000-0000-000000000085', '10000000-0000-0000-0000-000000000013',
 '2023-04-01 10:00:00+00',
 'Hola Leandro, trabajamos juntos en la pasantía. ¿Podrías validar mi habilidad en Marketing Digital?',
 'COWORKER', 'COMPLETED'),
('B0000000-0000-0000-0000-000000000011', '10000000-0000-0000-0000-000000000011',
 '20000000-0000-0000-0000-000000000086', '10000000-0000-0000-0000-000000000013',
 '2023-04-02 10:00:00+00',
 'Hola Leandro, ¿podrías también validar mi gestión de redes sociales?',
 'COWORKER', 'COMPLETED'),
('B0000000-0000-0000-0000-000000000012', '10000000-0000-0000-0000-000000000011',
 '20000000-0000-0000-0000-000000000087', '10000000-0000-0000-0000-000000000013',
 '2023-04-03 10:00:00+00',
 'Hola Leandro, ¿podrías validar mi experiencia con Meta Ads?',
 'COWORKER', 'COMPLETED'),
('B0000000-0000-0000-0000-000000000013', '10000000-0000-0000-0000-000000000011',
 '20000000-0000-0000-0000-000000000088', '10000000-0000-0000-0000-000000000013',
 '2023-04-04 10:00:00+00',
 'Hola Leandro, ¿podrías validar mi uso de Google Analytics?',
 'COWORKER', 'COMPLETED'),
('B0000000-0000-0000-0000-000000000014', '10000000-0000-0000-0000-000000000011',
 '20000000-0000-0000-0000-000000000089', '10000000-0000-0000-0000-000000000013',
 '2023-04-05 10:00:00+00',
 'Hola Leandro, ¿podrías validar mi capacidad de análisis de métricas?',
 'COWORKER', 'COMPLETED')
ON CONFLICT (id) DO NOTHING;


-- ----- Validaciones (solo para solicitudes COMPLETED) -----
INSERT INTO validation (id, validation_request_id, candidate_id, skill_id, validator_id,
                        assigned_level, comment, created_at)
VALUES
-- Carlos valida el Python de Guillermo
('C0000000-0000-0000-0000-000000000001', 'B0000000-0000-0000-0000-000000000001',
 '10000000-0000-0000-0000-000000000007', '20000000-0000-0000-0000-000000000004',
 '10000000-0000-0000-0000-000000000003',
 'COLABORADOR',
 'Guillermo tiene una base sólida y aprende muy rápido. Excelente actitud para crecer.',
 '2023-03-02 10:00:00+00'),

-- Alicia valida el Java de Flavia
('C0000000-0000-0000-0000-000000000002', 'B0000000-0000-0000-0000-000000000003',
 '10000000-0000-0000-0000-000000000006', '20000000-0000-0000-0000-000000000001',
 '10000000-0000-0000-0000-000000000001',
 'EJECUTOR_AUTONOMO',
 'Flavia demuestra ejecución autónoma sólida en tareas backend Java. Cumple objetivos sin supervisión.',
 '2023-03-16 10:00:00+00'),

-- Ignacio valida el DevOps de Diana (validación cruzada)
('C0000000-0000-0000-0000-000000000003', 'B0000000-0000-0000-0000-000000000004',
 '10000000-0000-0000-0000-000000000004', '20000000-0000-0000-0000-000000000064',
 '10000000-0000-0000-0000-000000000009',
 'EJECUTOR_AUTONOMO',
 'Diana maneja pipelines y automatización con soltura. Tiene criterio propio para resolver incidentes.',
 '2023-03-19 10:00:00+00'),

-- Diana valida el AWS de Ignacio (validación cruzada inversa)
('C0000000-0000-0000-0000-000000000004', 'B0000000-0000-0000-0000-000000000005',
 '10000000-0000-0000-0000-000000000009', '20000000-0000-0000-0000-000000000040',
 '10000000-0000-0000-0000-000000000004',
 'LIDER',
 'Ignacio gestiona infraestructura AWS compleja y guía al equipo en decisiones de arquitectura cloud.',
 '2023-03-20 10:00:00+00'),

-- Guillermo valida el Teamwork de Roberto (validación blanda cruzada)
('C0000000-0000-0000-0000-000000000005', 'B0000000-0000-0000-0000-000000000006',
 '10000000-0000-0000-0000-000000000002', '20000000-0000-0000-0000-000000000069',
 '10000000-0000-0000-0000-000000000007',
 'LIDER',
 'Roberto es un excelente compañero de equipo. Siempre disponible para ayudar y facilita la colaboración.',
 '2023-03-21 10:00:00+00'),

-- Carlos valida el Mentoring de Julieta (validación blanda cruzada)
('C0000000-0000-0000-0000-000000000006', 'B0000000-0000-0000-0000-000000000007',
 '10000000-0000-0000-0000-000000000010', '20000000-0000-0000-0000-000000000075',
 '10000000-0000-0000-0000-000000000003',
 'REFERENTE',
 'Julieta es una mentora excepcional. Su guía fue clave en mi desarrollo profesional. Referente absoluta.',
 '2023-03-23 10:00:00+00'),

-- Alicia valida el Problem Solving de Helena (validación blanda cruzada)
('C0000000-0000-0000-0000-000000000007', 'B0000000-0000-0000-0000-000000000008',
 '10000000-0000-0000-0000-000000000008', '20000000-0000-0000-0000-000000000070',
 '10000000-0000-0000-0000-000000000001',
 'LIDER',
 'Helena resuelve problemas complejos de base de datos con método y rapidez. Lidera la resolución en incidentes críticos.',
 '2023-03-26 10:00:00+00'),

-- ========== Validaciones de Martina (Demo Final) ==========
-- Leandro valida las 5 habilidades de Martina

-- Leandro valida Marketing Digital de Martina
('C0000000-0000-0000-0000-000000000008', 'B0000000-0000-0000-0000-000000000010',
 '10000000-0000-0000-0000-000000000011', '20000000-0000-0000-0000-000000000085',
 '10000000-0000-0000-0000-000000000013',
 'COLABORADOR',
 'Martina demostró durante su pasantía un buen entendimiento de los fundamentos del marketing digital. Tiene potencial para crecer.',
 '2023-04-02 10:00:00+00'),

-- Leandro valida Gestión de Redes Sociales de Martina
('C0000000-0000-0000-0000-000000000009', 'B0000000-0000-0000-0000-000000000011',
 '10000000-0000-0000-0000-000000000011', '20000000-0000-0000-0000-000000000086',
 '10000000-0000-0000-0000-000000000013',
 'COLABORADOR',
 'Martina gestionó las redes sociales de varios clientes durante la pasantía con buenos resultados.',
 '2023-04-03 10:00:00+00'),

-- Leandro valida Meta Ads de Martina
('C0000000-0000-0000-0000-000000000010', 'B0000000-0000-0000-0000-000000000012',
 '10000000-0000-0000-0000-000000000011', '20000000-0000-0000-0000-000000000087',
 '10000000-0000-0000-0000-000000000013',
 'COLABORADOR',
 'Martina colaboró en campañas de Meta Ads, aprendiendo rápidamente a optimizar anuncios.',
 '2023-04-04 10:00:00+00'),

-- Leandro valida Google Analytics de Martina
('C0000000-0000-0000-0000-000000000011', 'B0000000-0000-0000-0000-000000000013',
 '10000000-0000-0000-0000-000000000011', '20000000-0000-0000-0000-000000000088',
 '10000000-0000-0000-0000-000000000013',
 'COLABORADOR',
 'Martina utilizó Google Analytics para elaborar reportes de rendimiento de campañas.',
 '2023-04-05 10:00:00+00'),

-- Leandro valida Análisis de Métricas de Martina
('C0000000-0000-0000-0000-000000000012', 'B0000000-0000-0000-0000-000000000014',
 '10000000-0000-0000-0000-000000000011', '20000000-0000-0000-0000-000000000089',
 '10000000-0000-0000-0000-000000000013',
 'COLABORADOR',
 'Martina demostró capacidad para interpretar métricas y proponer mejoras basadas en datos.',
 '2023-04-06 10:00:00+00')
ON CONFLICT (id) DO NOTHING;


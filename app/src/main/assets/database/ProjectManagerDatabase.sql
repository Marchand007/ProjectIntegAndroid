BEGIN TRANSACTION;
DROP TABLE IF EXISTS "Employee";
CREATE TABLE IF NOT EXISTS "Employee" (
	"name"	TEXT NOT NULL UNIQUE,
	"manager_name"	TEXT,
	PRIMARY KEY("name")
);
DROP TABLE IF EXISTS "Project";
CREATE TABLE IF NOT EXISTS "Project" (
	"id"	INTEGER NOT NULL UNIQUE,
	"name"	TEXT NOT NULL,
	"client"	TEXT,
	"deadlineDate"	TEXT,
	PRIMARY KEY("id" AUTOINCREMENT)
);
DROP TABLE IF EXISTS "EmployeeProject";
CREATE TABLE IF NOT EXISTS "EmployeeProject" (
	"employee_name"	TEXT NOT NULL,
	"project_id"	INTEGER NOT NULL,
	"is_active"	INTEGER NOT NULL,
	"priority"	INTEGER NOT NULL DEFAULT 2,
	PRIMARY KEY("employee_name","project_id"),
	FOREIGN KEY("employee_name") REFERENCES "Employee"("name"),
	FOREIGN KEY("project_id") REFERENCES "Project"("id")
);
INSERT INTO "Employee" ("name","manager_name") VALUES ('Maxime Marchand',NULL);
INSERT INTO "Employee" ("name","manager_name") VALUES ('Francis Maynard',NULL);
INSERT INTO "Employee" ("name","manager_name") VALUES ('David Beaudry',NULL);
INSERT INTO "Employee" ("name","manager_name") VALUES ('Raphael Chanard Lamothe',NULL);
INSERT INTO "Employee" ("name","manager_name") VALUES ('Maxime Roy',NULL);
INSERT INTO "Project" ("id","name","client","deadlineDate") VALUES (1,'Développement d''un nouveau produit électronique','Fabricant d''appareils électroniques grand public','2023-08-31');
INSERT INTO "Project" ("id","name","client","deadlineDate") VALUES (2,'Conception d''un système de gestion des stocks automatisé','Entreprise de logistique et de distribution','2023-07-15');
INSERT INTO "Project" ("id","name","client","deadlineDate") VALUES (3,'Construction d''un pont suspendu','Agence gouvernementale des transports','2024-01-31');
INSERT INTO "Project" ("id","name","client","deadlineDate") VALUES (4,'Développement d''une application mobile de réservation de voyages','Agence de voyages en ligne','2023-10-30');
INSERT INTO "Project" ("id","name","client","deadlineDate") VALUES (5,'Développement d''une application mobile de réservation de voyages','Agence de voyages en ligne','2023-10-30');
INSERT INTO "Project" ("id","name","client","deadlineDate") VALUES (6,'Conception d''un réseau informatique pour une entreprise','Société de services informatiques','2023-09-15');
INSERT INTO "Project" ("id","name","client","deadlineDate") VALUES (7,'Mise en place d''un système de production d''énergie solaire','Compagnie d''énergie renouvelable','2024-03-31');
INSERT INTO "Project" ("id","name","client","deadlineDate") VALUES (8,'Amélioration des processus de fabrication dans une usine','Fabricant industriel','2023-12-31');
INSERT INTO "Project" ("id","name","client","deadlineDate") VALUES (9,'Développement d''un logiciel de gestion des ressources humaines','Entreprise de services RH','2023-11-30');
INSERT INTO "Project" ("id","name","client","deadlineDate") VALUES (10,'Conception d''une nouvelle voiture électrique','Fabricant automobile','2024-02-28');
INSERT INTO "Project" ("id","name","client","deadlineDate") VALUES (11,'Mise en place d''un système de sécurité avancé dans un bâtiment','Entreprise de sécurité ou institution gouvernementale','2023-09-30');
INSERT INTO "Project" ("id","name","client","deadlineDate") VALUES (12,'Développement d''un système de contrôle de la qualité pour l''industrie pharmaceutique','Entreprise pharmaceutique','2023-12-15');
INSERT INTO "Project" ("id","name","client","deadlineDate") VALUES (13,'Conception d''un réseau de télécommunications à large bande','Opérateur de télécommunications','2024-05-31');
INSERT INTO "Project" ("id","name","client","deadlineDate") VALUES (14,'Développement d''une plateforme de commerce électronique','Entreprise de vente au détail en ligne','2023-10-15');
INSERT INTO "Project" ("id","name","client","deadlineDate") VALUES (15,'Amélioration de l''efficacité énergétique d''un bâtiment','Propriétaire immobilier ou entreprise de gestion immobilière','2024-04-30');
INSERT INTO "Project" ("id","name","client","deadlineDate") VALUES (16,'Conception d''un système de traitement des eaux usées','Agence de l''eau ou entreprise de gestion des eaux','2023-11-30');
INSERT INTO "EmployeeProject" ("employee_name","project_id","is_active","priority") VALUES ('David Beaudry',3,1,2);
INSERT INTO "EmployeeProject" ("employee_name","project_id","is_active","priority") VALUES ('David Beaudry',11,1,2);
INSERT INTO "EmployeeProject" ("employee_name","project_id","is_active","priority") VALUES ('David Beaudry',5,1,1);
INSERT INTO "EmployeeProject" ("employee_name","project_id","is_active","priority") VALUES ('Maxime Marchand',3,0,2);
INSERT INTO "EmployeeProject" ("employee_name","project_id","is_active","priority") VALUES ('Maxime Marchand',6,1,2);
INSERT INTO "EmployeeProject" ("employee_name","project_id","is_active","priority") VALUES ('Maxime Marchand',11,1,2);
INSERT INTO "EmployeeProject" ("employee_name","project_id","is_active","priority") VALUES ('Maxime Marchand',12,0,3);
INSERT INTO "EmployeeProject" ("employee_name","project_id","is_active","priority") VALUES ('Maxime Roy',3,1,2);
INSERT INTO "EmployeeProject" ("employee_name","project_id","is_active","priority") VALUES ('Maxime Roy',4,1,2);
INSERT INTO "EmployeeProject" ("employee_name","project_id","is_active","priority") VALUES ('Maxime Roy',6,1,2);
INSERT INTO "EmployeeProject" ("employee_name","project_id","is_active","priority") VALUES ('Maxime Roy',10,0,3);
INSERT INTO "EmployeeProject" ("employee_name","project_id","is_active","priority") VALUES ('Maxime Roy',14,1,2);
INSERT INTO "EmployeeProject" ("employee_name","project_id","is_active","priority") VALUES ('Francis Maynard',1,1,2);
INSERT INTO "EmployeeProject" ("employee_name","project_id","is_active","priority") VALUES ('Francis Maynard',4,0,1);
INSERT INTO "EmployeeProject" ("employee_name","project_id","is_active","priority") VALUES ('Francis Maynard',11,1,2);
COMMIT;

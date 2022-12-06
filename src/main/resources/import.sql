insert into user_table(user_id,user_email,user_name,user_password) values (1,'test@gmail.com','c21106784','12345678');
insert into community_table(community_id,community_image,description,community_name) values (1,'project-bg.png','Wales Community Description','Wales Community');
insert into community_table(community_id,community_image,description,community_name) values (2,'Architecture-project.jpg','Architecture-project Description','Architecture Community');
insert into community_table(community_id,community_image,description,community_name) values (3,'computing.jpg','Computing Description','Computing Community');
insert into community_table(community_id,community_image,description,community_name) values (4,'netflix-cowboy-bebop.jpg','Anime Description','Anime Community');

insert into project_table(project_id,creator_userid,description,funds_received,funds_required,project_name,project_cover_image,community_id) values (1,1,'Project E Description',1000,8000,'Project E','t.jpg',1);
insert into project_table(project_id,creator_userid,description,funds_received,funds_required,project_name,project_cover_image,community_id) values (2,1,'Leeds Games Description',4000,8000,'Leeds games','Leeds-games-jam-image.jpg',1);
insert into project_table(project_id,creator_userid,description,funds_received,funds_required,project_name,project_cover_image,community_id) values (3,1,'Cowboy-bebop Description',3000,8000,'CowBoy','netflix-cowboy-bebop.jpg',1);
insert into project_table(project_id,creator_userid,description,funds_received,funds_required,project_name,project_cover_image,community_id) values (4,1,'Anna Description',2000,8000,'Anna','Anna.jpg',1);
insert into project_table(project_id,creator_userid,description,funds_received,funds_required,project_name,project_cover_image,community_id) values (5,1,'Computing Description',1000,8000,'Project E','computing.jpg',1);
insert into project_table(project_id,creator_userid,description,funds_received,funds_required,project_name,project_cover_image,community_id) values (6,1,'Architecture Description',1000,8000,'Project E','Architecture-project.jpg',1);

insert into event_table(event_id,about_section,event_address,event_contributors,creator_userid,event_date,event_image,event_name,community_id,project_id) values (1,'BO5 team competition','Cardiff, Wales','Yanguo Cao',1,'2022/12/06','dota.jpg','Dota2 Competition',NULL,2);
insert into event_table(event_id,about_section,event_address,event_contributors,creator_userid,event_date,event_image,event_name,community_id,project_id) values (2,'Welcome to share your unique design','Cardiff, Wales','Yanguo Cao',1,'2022/12/06','mc.jpg','Minecraft Architecture',NULL,2);

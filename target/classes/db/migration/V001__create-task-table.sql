create table task (
  id bigint not null auto_increment,
  description varchar(255) not null,
  status varchar(20) not null,
  created_At datetime not null,
  updated_At datetime,

  primary key (id)
);
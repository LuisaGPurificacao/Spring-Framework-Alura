create database vollmed_api;

use vollmed_api;

select * from medicos;
select * from pacientes;
select * from usuarios;

insert into usuarios values (1, 'ana.souza@voll.med', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.');

commit;
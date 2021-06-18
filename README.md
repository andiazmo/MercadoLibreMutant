# MercadoLibreMutant
EXAMEN MERCADOLIBRE

El presente repositorio esta diseñado para gestionar EL código fuente  relacionada con el examen
de ingreso a Mercado Libre. 

Integrantes: Anyelo Diaz Monroy  anyelodiazm@gmail.com (+57) 3013040739

Stack Tecnologico
* Java 1.8
* Spring Boot 

Aplicación desplegada en AWS:

http://mutant-env.eba-juf6hipc.us-east-2.elasticbeanstalk.com/mutant/

Funcionamiento de la Aplicación

Para ejecutar la aplicación se debe utilizar una herramienta de testeo de API's como postmam
+ A la URL: http://mutant-env.eba-juf6hipc.us-east-2.elasticbeanstalk.com/mutant/, hacer una petición
POST, que tenga como body un JSON con la siguiente estructura:
{
"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}
+ Lanzar la petición el sistema devuelve un HTTP 200 OK, para las matrices que cumplan con las validaciones
(cuadradas, con caracteres 'A,T,G,C') y con una secuencia de de cuatro letras iguales.

+De lo contrario el sistema devuelve un HTTP 403-Forbidden




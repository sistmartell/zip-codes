# zip-codes / Backend Coding Challenge 

This is a rest api that basically performs two operations:
* Get all settlements information from a zip code
* Get all the settlements information related to a name which can be the same settlement, city or municipality  `(Additional to requested) `


## How does it work?

_It was decided to use web scraping to obtain the information, this is a better option than load the full database._

_This is the data source used to make web scraping: [https://micodigopostal.org/buscarcp](https://micodigopostal.org/buscarcp)_

_An H2 database is used to optimize queries,
that is to say, when a query by zip code returns a result it is stored for use in future queries and not use scraping._

### Used tools 🔧


* [Spring Boot 2.3.0](https://spring.io/projects/spring-boot) 
* [Maven](https://maven.apache.org/) - 
Dependency manager
* [Jsoup](https://jsoup.org/download) -  Web scraping
* [H2](https://www.h2database.com/html/main.html) -  Database
* [Swagger](https://swagger.io/) -  Documentation



## Running tests

_The api is published in Google Cloud_


 * **[Documentation Swagger](https://deft-gearbox-279600.ue.r.appspot.com/swagger-ui.html#/)** 


#### Get all settlements information from a zip code:



* _This is an example of a request for an existing zip code:_


```
https://deft-gearbox-279600.ue.r.appspot.com/zip-codes/55690
```
_Response:_
```
http status: 200
```

```json
{
    "codigo_postal": "55673",
    "municipio": "Hueypoxtla",
    "ciudad": "-",
    "estado": "Estado de México",
    "asentamientos": [
        {
            "asentamiento": "Roma",
            "tipo_asentamiento": "Colonia",
            "zona": "Urbana"
        },
        {
            "asentamiento": "Centenario",
            "tipo_asentamiento": "Colonia",
            "zona": "Rural"
        },
        {
            "asentamiento": "España",
            "tipo_asentamiento": "Colonia",
            "zona": "Urbana"
        },
        {
            "asentamiento": "Juárez",
            "tipo_asentamiento": "Colonia",
            "zona": "Rural"
        }
    ]
}
```

* _Now with a non-existent zip code:_

```
https://deft-gearbox-279600.ue.r.appspot.com/zip-codes/00000
```
_Response:_
```
http status: 404
```

```json
{}
```


#### Get all the settlements information related to a name:

* _Let's test passing as a parameter: Hueypoxtla:_

```
https://deft-gearbox-279600.ue.r.appspot.com/zip-codes/asentamiento/hueypoxtla
```
_Response:_
```
http status: 200
```

```json
[
    {
        "asentamiento": "Hueypoxtla",
        "tipoAsentamiento": "Pueblo",
        "codigoPostal": "55670",
        "municipio": "55670",
        "ciudad": "Hueypoxtla",
        "zona": "Estado de México",
        "estado": "Estado de México"
    },
    {
        "asentamiento": "Huicalco",
        "tipoAsentamiento": "Colonia",
        "codigoPostal": "55670",
        "municipio": "55670",
        "ciudad": "Hueypoxtla",
        "zona": "Estado de México",
        "estado": "Estado de México"
    },
    {
        "asentamiento": "Roma",
        "tipoAsentamiento": "Colonia",
        "codigoPostal": "55673",
        "municipio": "55673",
        "ciudad": "Hueypoxtla",
        "zona": "Estado de México",
        "estado": "Estado de México"
    },
    {
        "asentamiento": "Centenario",
        "tipoAsentamiento": "Colonia",
        "codigoPostal": "55673",
        "municipio": "55673",
        "ciudad": "Hueypoxtla",
        "zona": "Estado de México",
        "estado": "Estado de México"
    },
    {
        "asentamiento": "España",
        "tipoAsentamiento": "Colonia",
        "codigoPostal": "55673",
        "municipio": "55673",
        "ciudad": "Hueypoxtla",
        "zona": "Estado de México",
        "estado": "Estado de México"
    },
    {
        "asentamiento": "Juárez",
        "tipoAsentamiento": "Colonia",
        "codigoPostal": "55673",
        "municipio": "55673",
        "ciudad": "Hueypoxtla",
        "zona": "Estado de México",
        "estado": "Estado de México"
    },
    {
        "asentamiento": "Casa Blanca (Ex-Hacienda Casa Blanca)",
        "tipoAsentamiento": "Pueblo",
        "codigoPostal": "55676",
        "municipio": "55676",
        "ciudad": "Hueypoxtla",
        "zona": "Estado de México",
        "estado": "Estado de México"
    },
    {
        "asentamiento": "San Miguel Tepetates",
        "tipoAsentamiento": "Colonia",
        "codigoPostal": "55679",
        "municipio": "55679",
        "ciudad": "Hueypoxtla",
        "zona": "Estado de México",
        "estado": "Estado de México"
    },
    {
        "asentamiento": "Santa María Ajoloapan",
        "tipoAsentamiento": "Pueblo",
        "codigoPostal": "55679",
        "municipio": "55679",
        "ciudad": "Hueypoxtla",
        "zona": "Estado de México",
        "estado": "Estado de México"
    },
    {
        "asentamiento": "San Francisco Zacacalco",
        "tipoAsentamiento": "Pueblo",
        "codigoPostal": "55680",
        "municipio": "55680",
        "ciudad": "Hueypoxtla",
        "zona": "Estado de México",
        "estado": "Estado de México"
    },
    {
        "asentamiento": "Tianguistongo",
        "tipoAsentamiento": "Pueblo",
        "codigoPostal": "55683",
        "municipio": "55683",
        "ciudad": "Hueypoxtla",
        "zona": "Estado de México",
        "estado": "Estado de México"
    },
    {
        "asentamiento": "San Pedro la Gloria",
        "tipoAsentamiento": "Ranchería",
        "codigoPostal": "55684",
        "municipio": "55684",
        "ciudad": "Hueypoxtla",
        "zona": "Estado de México",
        "estado": "Estado de México"
    },
    {
        "asentamiento": "Tezontlalpa de Zapata (Tezontlalpan)",
        "tipoAsentamiento": "Pueblo",
        "codigoPostal": "55685",
        "municipio": "55685",
        "ciudad": "Hueypoxtla",
        "zona": "Estado de México",
        "estado": "Estado de México"
    },
    {
        "asentamiento": "Nopala",
        "tipoAsentamiento": "Pueblo",
        "codigoPostal": "55689",
        "municipio": "55689",
        "ciudad": "Hueypoxtla",
        "zona": "Estado de México",
        "estado": "Estado de México"
    },
    {
        "asentamiento": "Jilotzingo",
        "tipoAsentamiento": "Pueblo",
        "codigoPostal": "55690",
        "municipio": "55690",
        "ciudad": "Hueypoxtla",
        "zona": "Estado de México",
        "estado": "Estado de México"
    },
    {
        "asentamiento": "El Carmen",
        "tipoAsentamiento": "Pueblo",
        "codigoPostal": "55694",
        "municipio": "55694",
        "ciudad": "Hueypoxtla",
        "zona": "Estado de México",
        "estado": "Estado de México"
    },
    {
        "asentamiento": "Emiliano Zapata (San José Bata)",
        "tipoAsentamiento": "Pueblo",
        "codigoPostal": "55697",
        "municipio": "55697",
        "ciudad": "Hueypoxtla",
        "zona": "Estado de México",
        "estado": "Estado de México"
    }
]
```


---
_Creado por_ [_Germán Martell_](https://github.com/Villanuevand) 


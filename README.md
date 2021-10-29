# mutant-api
- Verifica si una persona es mutante o no
- Devuelve estadisticas de las verificaciones

## End points:
**POST to /api/mutant/**

{
“dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}

En caso de verificar un mutante, debería devolver un HTTP 200-OK, en caso contrario un
403-Forbidden

**GET to /api/mutant/stats**

devuelva un Json con las estadísticas de las
verificaciones de ADN: {“count_mutant_dna”:4, “count_human_dna”:10: “ratio”:0.4}


## Ejecucion local:

### Maven
- Intalar las dependencias del proyecto con maven: _mvn clean install_
- Ejecurar la **com.mutant.App** en el paquete **mutant-app**

### Docker
- Crear la imagen desde el Archivo **Dockerfile**. En la raiz del proyecto ejecutar: **docker build -t mutant .**
- Ejecutar el container: **docker run -dp 8080:8080 mutant**

## Swagger
http://localhost:8080/api/mutant/swagger-ui.html

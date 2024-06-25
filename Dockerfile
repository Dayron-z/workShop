#Imagen modelo -> vamos a crear una imagen a partir de otra imagen ya existente.
FROM eclipse-temurin:17.0.11_9-jdk
#Beneficio = Estamos haciendo uso de una imagen que ya tiene java instalado.


#Este comando no hace nada, es informativo.
EXPOSE 8080



# Definir directorio raiz del contenedor -> (root -> Nosotros definimos este nombre)
WORKDIR /root

#Copiar y pegar archivos dentro del contenedor (Lo que queremos que lleve el contenedor - dependencias - configuraciones etc.)
#Pegamos en el directorio del contenedor que definimos previamente

COPY ./pom.xml /root
    # /.mvn 0 -> Se hace para que cree una carpeta dentro de root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root

#Descargar dependencias sin que construya el proyecto
#Nos sirve para que al copiar el codigo fuente ya las dependencias van a estar disponibles.
RUN ./mvnw dependency:go-offline
#Copiar codigo fuente dentro del contenedor
COPY ./src /root/src
#Construir nuestra apliación
#En este caso nos  saltamos los test porque no contamos con test unitarios .
RUN ./mvnw clean install -DskipTests

#Levantar nuestra apliación cuando el contenedor inicie
#El entryPoint se va a ejecutar unicamente cuando el contenedor inicie
ENTRYPOINT ["java", "-jar", "WorkShop-0.0.1-SNAPSHOT-tests.jar"]
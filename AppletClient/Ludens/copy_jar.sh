#!/bin/bash
desktopProject=/media/Data/DropBox/Dropbox/Projects/Desktop/Ludens
webProject=/media/Data/DropBox/Dropbox/Projects/Web/Utopia
echo "Cambiando de dir"
cd $desktopProject/Test/

echo "Borrando Directorio Ludens"
rm -rf MapLudens

echo "Borrando  Jar Ludens"
rm -rf MapLudens.jar

echo "Creando  Directorio Ludens"
mkdir MapLudens

echo "Copiando nuevo .jar"
cp $desktopProject/MapLudens/dist/MapLudens.jar ./

echo "Descomprimiendo nuevo Mapludens jar "
unzip MapLudens.jar -d ./MapLudens

echo "Copiando Genuts library a MapLudens jar "
cp -Rv ./com ./MapLudens

echo "Copiando net library a MapLudens jar "
cp -Rv ./net ./MapLudens

echo "Copiando javax library a MapLudens jar "
cp -Rv ./javax ./MapLudens

echo "Copiando org library a MapLudens jar "
cp -Rv ./org ./MapLudens

echo "Borrando  Jar Ludens"
rm -rf MapLudens.jar

cd MapLudens

echo "Creando Jar"
zip -r ../MapLudensTmp.jar META-INF co com javax net org



cd ..

echo "Signing Jar"
jarsigner -keystore ludens2012 -signedjar ./MapLudens.jar ./MapLudensTmp.jar ludens2012

echo "Borrando  Temp Jar Ludens"
rm -rf MapLudensTmp.jar


echo "Copiando a el proyecto de eclipse"
cp -r ./MapLudens.jar $webProject/WebContent/applets/"$1".jar

echo "Done!!!"

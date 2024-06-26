# OpenMap, Swing
## Практическое задание АО «СПИИРАН-НТБВТ»
### Документация
![image](https://github.com/blabla9111/Practice_SPIIRAN_Task_2/assets/92872423/4fe30800-a8db-4b75-98f8-11a6d900cfe7)
#
Данное приложение – это клиентское приложение, написанное на Swing, которое включает в себя картографический фреймворк OpenMap. Главным компонентом приложения является цифровая интерактивная карта (OpenMap предоставляет базовый набор инструментов для работы с картой). Создание и удаление объектов на карте осуществляется через стандартные инструменты OpenMap. Объект, которые редактируется загорается бирюзовым цветом.
#
На карте 3 слоя:
* MyOMPointLayer – слой растровых(точечных) объектов (MyOMPoint)  
MyOMPoint 
    * Название (name)
    * Координаты (lat, lon)
    * Радиус (radius)
    * Курс (угол поворота)(course)
  
    Для создания MyOMPoint нужно создать объект OMPoint (Point) в слое MyOMPointLayer. Затем на карте появится только что созданный объект. И нужно его отредактировать в соответствии и необходимыми 
    характеристиками (см.рис ниже). Если элемент не отобразился, то либо нужно увеличить масштаб (для перерисовки карты), либо поискать по всей карте элемент.
  ![image](https://github.com/blabla9111/Practice_SPIIRAN_Task_2/assets/92872423/0a5686e4-2983-420b-b4a2-00183d2f0af0)
  ![image](https://github.com/blabla9111/Practice_SPIIRAN_Task_2/assets/92872423/4839cd65-6858-4800-970e-a04e1bde8c61)





* MyOMPolyLayer – слой полигонов (MyOMPoly)  
  MyOMPoly
    * Название (name)
    * Координаты X точек полигона (xs)
    * Координаты Y точек полигона (ys)
  
    Для создания MyOMPoly нужно создать объект OMSpline (Splines) с необходимым кол-вом точек в слое MyOMPolyLayer. Затем на карте появится только что созданный объект. И нужно его отредактировать в соответствии      и необходимыми характеристиками (см.рис ниже). Если элемент не отобразился, то либо нужно увеличить масштаб (для перерисовки карты), либо поискать по всей карте элемент.

    ![image](https://github.com/blabla9111/Practice_SPIIRAN_Task_2/assets/92872423/5ce7f968-0c80-49dc-8389-74043f7465de)
    ![image](https://github.com/blabla9111/Practice_SPIIRAN_Task_2/assets/92872423/1dff7ae7-38c5-4893-8c0a-a3dc057d8b81)

* MyOMSectorLayer – слой секторов (MyOMSector)  
   MyOMSector
    * Название(name)
    * Координата X центра (centerX)
    * Координата Y центра (centerY)
    * X радиус (radiusX)
    * Y радиус (radiusY)
    
    Для создания MyOMSector нужно создать объект OMCircle (Circle) в слое MyOMSectorLayer. Затем на карте появится только что созданный объект. И нужно его отредактировать в соответствии и необходимыми        
    характеристиками. Если элемент не отобразился, то либо нужно увеличить масштаб (для перерисовки карты), либо поискать по всей карте элемент.
  ![image](https://github.com/blabla9111/Practice_SPIIRAN_Task_2/assets/92872423/21e7151a-23eb-4601-8dd9-ad8f42dcc60c)
  ![image](https://github.com/blabla9111/Practice_SPIIRAN_Task_2/assets/92872423/9f1184ea-aada-4a11-b799-2e1d5c14405b)

## Сборка и запуск приложения
mvn clean  
mvn assembly:assembly  
java -cp ./target/openMapApp-1.0-SNAPSHOT-jar-with-dependencies.jar OpenMap  
(Появятся два окна, в одном из них отобразится нужная карта)  
Если не получаетсся собрать через maven, то можно использовать готовый jar-файл  
java -jar openMapApp.jar


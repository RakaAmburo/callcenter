# CALL CENTER

Descripci�n general:
Este modelo de call center se compone principalmente por la clase Dispatcher encargada de manejar tanto las entrada de las llamadas  como el encolamiento y procesamiento de las  mismas. Para eso se vale de dos queues principales para almacenar las llamadas y para almacenar los empleados encargados de atender. El queue de llamadas va liberando las llamadas a medida que van llegando con su metodo take. El contendedor de empleados va liberando los empleados ordenados por su prioridad relacionada con su cargo. Para esto uso un priorityqueue que ordena sus elementos gracias a la interface comparable en employees, compara la priodidad fijada en cada implementacion: operador, supervisor y director. La clase call extiende de runneable y va ser la encargada de procesar la llamada en s�, estas se asignaran a un ejecutable al que se le puede configurar la cantidad de thread concurrentes.
Al instanciar dispacher: (Dispatcher dispatcher = new Dispatcher(attenders, 10, 15);) se definir�n los empleados disponibles, cantidad de llamadas concurrentes (capacidad del executor) y cantidad de lineas disponibles (llamadas qeu pueden ingresar al call center sin ser revotadas con un mensaje de que todas las linease est�n ocupadas).
Cree la clase employee status para dar la posibilidad de varios status del empleado no solamente avail or unavaliable, tambi�n podr�a ser en almuerzo y dem�s. NO LLEGUE A EMPEARLA. Tambi�n hay una clase CallCenter que use para probar r�pidamente.
Pueden encontrar tambi�n documentaci�n en el c�digo.

El diagrama del uml lo puden encontrar en el root del proyecto en formato CallCenter.jpg.

* Soluci�n sobre que pasa con una llamada si no hay empleados libres:
Eso lo pens� usando el executor, al que se le pueden asignar n threads, los que ser�n activados una vez encolados. Los excedentes quedaran en espera a que un              empleado, sea cual sea su rango, se libere. M�s o menos como cuando llamas a un call center y quedas en espera. Algo parecido ocurre con las llamadas entrantes, se encolan en un queue (ArrayBlockingQueue) al que se le puede definir el l�mite de l�neas.


Conclusi�n: Variando los timpos de time out en TimeOut.java, la composicion de los empleados(operador, supervisor y director) y el tiempo promedio de llamada, se puede observar que composicion de equipo se necestia. ej: si las llamadas no son muchas, no deberia tener que atender un director, y escasamente un supervisor.
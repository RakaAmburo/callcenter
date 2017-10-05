# CALL CENTER

Descripción general:
Este modelo de call center se compone principalmente por la clase Dispatcher encargada de manejar tanto las entrada de las llamadas  como el encolamiento y procesamiento de las  mismas. Para eso se bale de dos queues principales para almacenar las llamadas y para almacenar los empleados encargados de atender. El queue de llamadas va liberando las llamadas a medida que van llegando con su metodo take. En el caso el contendedor de empemados va liberando los empleados ordenados por su prioridad relacionada con su cargo. Para esto uso un priorityqueue que ordena sus elementos gracias a la interface comparable en employees, compara la priodidad fijada en cada implementacion: operador, supervicior y director. La clase call extiende de runneable y va ser la encargada de procesar la llamada en si, estas se asignaran a un ejecutable al qeu se le puede configurar la cantidad de thread concurrentes.
Al instaciar dispacher: Dispatcher dispatcher = new Dispatcher(attenders, 10, 15); se definiran los empleados disponibles, cantidad de llamadas concurrentes (capacidad del executor) y cantidad de lineas disponibles(llamadas qeu pueden ingresar al call center sin ser revotadas con un mensaje de que todas als linease estan ocupadas).
Cree la clase employee status para dar la posibildad de varios status del empleado no solamente avail or unavaliable, tambien podria ser en almuerzo y demás. NO LLEGUE A EMPEARLA. Tambien hay una clase CallCenter que use para probar rapidamente.
Pueden encontrar documentación en el código.

El diagrama del uml lo puden encontrar en el root del projecto en formato jpg.

* Solución sobre que pasa con una llamada si no hay empleados libres:
Eso lo pense usando el executor, al que se le pueden asignar x threads que seran los que seran activados una vez encolados. Los exedentes quedaran en espera a que un              emplleado, sea cual sea su rango, se libere. Mas o menos como cuando llamas a un call center y qeudas en espera. Algo parecido ocurre con las llamdas entrantes, se encolan en un queue (ArrayBlockingQueue) al que se le puede definir el limite de lineas. 
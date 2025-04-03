// 1. Singleton implmentation using object keyword 

// An object declaration is a concise way of creating a singleton class without the need to define a class and a companion object.
object Singleton {
    init {
        // to initialize 
    }
    fun doSomething() = "Doing something"
}

// 2. Singleton implmentation using companion object

// private constructor prevents direct instantiation 
class SingletonClass private constructor() {
    
    // companion object can be used to store static members and methods for the class 
    companion object {

        // The @Volatile annotation is needed to ensure that the instance property is updated atomically. This prevents other threads from creating more instances and breaking the singleton pattern. 
        @Volatile
        private var instance: SingletonClass? = null

        fun getInstance() =
            // We need the synchronized keyword in the static getInstance method to prevent accessing the method from multiple threads simultaneously.
            instance ?: synchronized(this) {
                instance ?: SingletonClass().also { instance = it }
            }
            // Double Locking: we have 2 null checks on instance, In a multithreaded application, it is possible that, in the meanwhile, another thread created the instance. Therefore, we need another null check, and only if the instance is still null we’ll create it.
    }
    fun doSomething() = "Doing something"
}

fun main() {
    val singleton = SingletonClass.getInstance()
    singleton.doSomething()
}


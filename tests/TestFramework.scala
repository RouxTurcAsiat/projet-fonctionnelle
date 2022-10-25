package tests

import io.AnsiColor._

class TestFramework:
    enum Result[+A]:
        case Success
        case Failure

    def assertEqual[A](a: A, b: A): Result[A] =
        if (a == b) then Result.Success
        else Result.Failure

    def output_result(result: Result[Any], name: String): Option[Unit] = result match 
        case Result.Success => 
            println(s"${GREEN_B}${BLACK} Passed ${RESET} $name")
            Some(())

        case Result.Failure =>
            println(s"${RED_B}${BLACK} Failed ${RESET} $name")
            None

    def main(args: Array[String]): Unit = 
        val methods = this.getClass.getMethods.filter(_.getName.toLowerCase contains "test")
        val success = methods
                        .map((x) => output_result(x.invoke(this).asInstanceOf[Result[Any]], x.getName))
                        .map(_ match 
                             case Some(_) => 1
                             case None => 0
                        ).sum

        println(s"\nTests:\t${GREEN}$success passed${RESET}, ${methods.length} total")

        if (methods.length != success) sys.exit(1)
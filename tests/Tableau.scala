package tests

import tests.TestFramework

object Tableau extends TestFramework:
    def firstTest: Result[Int] =
        this.assertEqual(1, 1)
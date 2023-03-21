package com.devpass.spaceapp

import androidx.core.content.ContextCompat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

/**
Ele cria uma lista de targets de test

- Rodou o unit Test
- Verifica se tem algum @Test
- Ele cria instancia de ExampleUnitTest#addition_isCorrect e adiciona a lista de testes
- Ele cria instancia de ExampleUnitTest#addition_isNotCorrect e adiciona a lista de testes
- Percorre e executa os metodos de @Test da lista
- Executa BeforeClasse se nao tiver sido executado
- Executa Before
- Executa @Test
- Execute After
- Executa AfterClass se nao tiver sido executado
 */

class Algorithm {

    fun doJob1(parameter: Boolean): List<Int> {
        return if (parameter) {
            listOf(Int.MAX_VALUE, Int.MIN_VALUE)
        } else {
            emptyList()
        }
    }
}

class AlgorithmTest {

    private val subject = Algorithm()

    @Test
    fun doJob1_WithParameterAsTrue_ShouldReturnListWithTwoItems() {
        // Arrange.
        val expected = 2

        // Act.
        val result = subject.doJob1(true)

        // Assert.
        assertEquals(expected, result.size)
    }

    @Test
    fun doJob1_WithParameterAsTrue_ShouldReturnListWithCorrectValues() {
        // Arrange
        val expected = listOf(Int.MAX_VALUE, Int.MIN_VALUE)

        // Act
        val result = subject.doJob1(true)

        // Assert
        assertEquals(expected, result)
    }

    @Test
    fun doJob1_WithParameterAsFalse_ShouldReturnListWithTwoItems() {
        // Arrange.
        val expected = 0

        // Act.
        val result = subject.doJob1(false)

        // Assert.
        assertEquals(expected, result.size)
    }

    @Test
    fun doJob1_WithParameterAsFalse_ShouldReturnListWithCorrectValues() {
        // Arrange
        val expected = emptyList<Int>()

        // Act
        val result = subject.doJob1(false)

        // Assert
        assertEquals(expected, result)
    }

    @Test
    fun addition_isNotCorrect() {
        assertNotEquals(5, 2 + 2)
    }

    fun subtraction_isNotCorrect() {
        assertNotEquals(5, 2 - 2)
    }
}

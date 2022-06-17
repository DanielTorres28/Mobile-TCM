package com.example.mobiletcm;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.mobiletcm.Login.LOginDTO;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    LOginDTO lOginDTO = new LOginDTO(1,"Daniel", "daniel@hotmail.com", "123456");
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
}
    @Test
    public void testeExisteUsuario() {
        lOginDTO.autenticar("daniel@hotmail.com", "123456");
    }

    @Test //é para poder fazer o Teste
    public void testeNaoExisteUsuario() {


    }
}
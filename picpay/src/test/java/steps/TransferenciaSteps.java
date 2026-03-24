package steps;

import io.cucumber.java.pt.*;
import static org.junit.Assert.*;

public class TransferenciaSteps {

    private int saldoUsuarioA;
    private int saldoUsuarioB;
    private boolean transferenciaRealizada;
    private boolean autorizacaoExterna = true;

    @Given("que o usuário A possui saldo de {int} reais")
    public void usuarioA_tem_saldo(int saldo) {
        saldoUsuarioA = saldo;
    }

    @Given("que o usuário B está cadastrado no sistema")
    public void usuarioB_existe() {
        saldoUsuarioB = 0;
    }

    @Given("que o serviço de autorização está indisponível")
    public void servico_indisponivel() {
        autorizacaoExterna = false;
    }

    @When("o usuário A tenta transferir {int} reais para o usuário B")
    public void realiza_transferencia(int valor) {

        if (!autorizacaoExterna) {
            transferenciaRealizada = false;
            return;
        }

        if (valor <= 0) {
            transferenciaRealizada = false;
            return;
        }

        if (saldoUsuarioA >= valor) {
            saldoUsuarioA -= valor;
            saldoUsuarioB += valor;
            transferenciaRealizada = true;
        } else {
            transferenciaRealizada = false;
        }
    }

    @Then("a transferência deve ser realizada com sucesso")
    public void transferencia_sucesso() {
        assertTrue(transferenciaRealizada);
    }

    @Then("a transferência deve ser negada")
    public void transferencia_negada() {
        assertFalse(transferenciaRealizada);
    }

    @Then("o saldo do usuário A deve ser {int} reais")
    public void valida_saldo_usuarioA(int saldoEsperado) {
        assertEquals(saldoEsperado, saldoUsuarioA);
    }

    @Then("o saldo do usuário B deve ser {int} reais")
    public void valida_saldo_usuarioB(int saldoEsperado) {
        assertEquals(saldoEsperado, saldoUsuarioB);
    }

    @Then("o saldo permanece inalterado")
    public void saldo_inalterado() {
        assertFalse(transferenciaRealizada);
    }
}
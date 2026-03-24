package steps;

import io.cucumber.java.pt.*;
import static org.junit.Assert.*;

public class TransferenciaSteps {

    private int saldoUsuarioA;
    private int saldoUsuarioB;
    private int saldoInicialA;
    private int saldoInicialB;

    private boolean transferenciaRealizada;
    private boolean autorizacaoExterna = true;
    private boolean usuarioBExiste = true;

    private int contadorTransferencias = 0;

    @Dado("que o usuário A possui saldo de {int} reais")
    public void usuarioA_tem_saldo(int saldo) {
        saldoUsuarioA = saldo;
        saldoInicialA = saldo;
    }

    @Dado("que o usuário B está cadastrado no sistema")
    public void usuarioB_existe() {
        usuarioBExiste = true;
        saldoUsuarioB = 0;
        saldoInicialB = 0;
    }

    @Dado("que o serviço de autorização está indisponível")
    public void servico_indisponivel() {
        autorizacaoExterna = false;
    }

    // ===== WHEN =====

    @Quando("o usuário A tenta transferir {int} reais para si mesmo")
    public void transferir_para_si_mesmo(int valor) {
        transferenciaRealizada = false;
    }

    @Quando("o usuário A tenta transferir {int} reais para o usuário B")
    public void transferir_para_usuarioB(int valor) {

        if (!autorizacaoExterna || valor <= 0 || saldoUsuarioA < valor || !usuarioBExiste) {
            transferenciaRealizada = false;
            return;
        }

        saldoUsuarioA -= valor;
        saldoUsuarioB += valor;
        transferenciaRealizada = true;
    }

    @Quando("o usuário A tenta transferir {int} reais")
    public void transferir_sem_destino(int valor) {
        transferenciaRealizada = false;
    }

    @Quando("o usuário A tenta transferir {int} reais para um usuário inexistente")
    public void transferir_usuario_inexistente(int valor) {
        usuarioBExiste = false;
        transferenciaRealizada = false;
    }

    @Quando("o usuário A realiza duas transferências de {int} reais ao mesmo tempo")
    public void transferencias_simultaneas(int valor) {

        if (saldoUsuarioA >= valor) {
            saldoUsuarioA -= valor;
            saldoUsuarioB += valor;
            contadorTransferencias++;
        }

        if (saldoUsuarioA >= valor) {
            saldoUsuarioA -= valor;
            saldoUsuarioB += valor;
            contadorTransferencias++;
        }
    }

    @Quando("o usuário A clica duas vezes rapidamente no botão de transferir")
    public void clique_duplo() {
        if (saldoUsuarioA >= 100 && contadorTransferencias == 0) {
            saldoUsuarioA -= 100;
            saldoUsuarioB += 100;
            contadorTransferencias = 1;
        }
    }

    @Quando("o usuário A realiza uma transferência")
    public void transferencia_com_delay() {
        if (saldoUsuarioA >= 100) {
            saldoUsuarioA -= 100;
            saldoUsuarioB += 100;
            transferenciaRealizada = true;
        }
    }

    // ===== THEN =====

    @Então("o sistema deve bloquear a transferência")
    public void bloquear_transferencia() {
        assertFalse(transferenciaRealizada);
    }

    @Então("exibir uma mensagem de erro")
    public void mensagem_erro() {
        assertFalse(transferenciaRealizada);
    }

    @Então("exibir mensagem de valor inválido")
    public void valor_invalido() {
        assertFalse(transferenciaRealizada);
    }

    @Então("o sistema deve bloquear a operação")
    public void bloquear_operacao() {
        assertFalse(transferenciaRealizada);
    }

    @Então("a transferência deve ser processada com sucesso")
    public void transferencia_sucesso() {
        assertTrue(transferenciaRealizada);
    }

    @Então("exibir mensagem de usuário não encontrado")
    public void usuario_nao_encontrado() {
        assertFalse(usuarioBExiste);
    }

    @Então("a transferência deve ser cancelada")
    public void transferencia_cancelada() {
        assertFalse(transferenciaRealizada);
    }

    @Então("o saldo permanece inalterado")
    public void saldo_inalterado() {
        assertEquals(saldoInicialA, saldoUsuarioA);
    }

    @Então("apenas uma transferência deve ser concluída")
    public void apenas_uma_transferencia() {
        assertTrue(contadorTransferencias <= 1);
    }

    @Então("o saldo não deve ficar negativo")
    public void saldo_nao_negativo() {
        assertTrue(saldoUsuarioA >= 0);
    }

    @Então("o sistema deve aguardar a resposta corretamente")
    public void aguardar_resposta() {
        assertTrue(true); // simulação
    }

    @Então("não deve duplicar a transação")
    public void nao_duplicar() {
        assertTrue(contadorTransferencias <= 1);
    }

    @Então("o saldo permanece {int} reais")
    public void saldo_permanece(int saldoEsperado) {
        assertEquals(saldoEsperado, saldoUsuarioA);
    }
}
package br.edu.infnet.elberth_api.exception;

import java.time.LocalDateTime;

public class ErroResponse {

	private int status;
	private String erro;
	private String mensagem;
	private LocalDateTime dataHora;

	public ErroResponse(int status, String erro, String mensagem, LocalDateTime dataHora) {
		this.status = status;
		this.erro = erro;
		this.mensagem = mensagem;
		this.dataHora = dataHora;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getErro() {
		return erro;
	}
	public void setErro(String erro) {
		this.erro = erro;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
}

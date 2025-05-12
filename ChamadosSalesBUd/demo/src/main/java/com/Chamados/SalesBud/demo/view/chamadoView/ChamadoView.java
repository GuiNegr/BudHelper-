package com.Chamados.SalesBud.demo.view.chamadoView;

import com.Chamados.SalesBud.demo.bean.DTO.UsuarioDto;

public interface ChamadoView {

    public void cadastroChamadoView(UsuarioDto usuarioLogado);

    public void fecharChamadoView();

    public void listarChamdosPorEmpresa();

    public void menuExportCsvEmpresa();

    public void listarChamadosAllView();
}

package br.com.educamil.controle;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import br.com.educamil.dao.HibernateUtil;
import br.com.educamil.dao.NotaDao;

import br.com.educamil.dao.NotaDaoImpl;
import br.com.educamil.entity.*;
import javax.faces.model.SelectItem;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

@ManagedBean(name = "notaC")
@ViewScoped
public class NotaControle {

    private Nota nota;
    private NotaDao notaDao;
    private Session sessao;
    private Disciplina disciplina;
    private Turma turma;
    private Aluno aluno;
    private List<Nota> notas;
    private List<Disciplina> disciplinas;
    private List<Aluno> alunos;
    private DataModel<Nota> modelNotas;
    private List<SelectItem> comboDisciplinas;
    private List<SelectItem> comboTurmas;
    private int aba;

    public NotaControle() {
        notaDao = new NotaDaoImpl();
    }

    public void salvar() {
        sessao = HibernateUtil.abrirSessao();
        try {
            notaDao.salvarOuAlterar(nota, sessao);
            nota = null;
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Notas Salvas com Sucesso", null));
            //modelturmas = null;
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar as notas", null));
        } finally {
            sessao.close();
        }

    }

    public void onTabChange(TabChangeEvent event) {
        if (event.getTab().getTitle().equals("Novo"))
			;
    }

    public void onTabClose(TabCloseEvent event) {
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public NotaDao getNotaDao() {
        return notaDao;
    }

    public void setNotaDao(NotaDao notaDao) {
        this.notaDao = notaDao;
    }

    public Session getSessao() {
        return sessao;
    }

    public void setSessao(Session sessao) {
        this.sessao = sessao;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public DataModel<Nota> getModelNotas() {
        return modelNotas;
    }

    public void setModelNotas(DataModel<Nota> modelNotas) {
        this.modelNotas = modelNotas;
    }

    public List<SelectItem> getComboDisciplinas() {
        return comboDisciplinas;
    }

    public void setComboDisciplinas(List<SelectItem> comboDisciplinas) {
        this.comboDisciplinas = comboDisciplinas;
    }

    public List<SelectItem> getComboTurmas() {
        return comboTurmas;
    }

    public void setComboTurmas(List<SelectItem> comboTurmas) {
        this.comboTurmas = comboTurmas;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public int getAba() {
        return aba;
    }

}

package br.com.alura.schedule.modelo;

import java.io.Serializable;


public class Student implements Serializable {
    private Long mId;
    private String mName;
    private String mAddress;
    private String mPhone;
    private String mWebsite;
    private Double mGrade;
    private String mPathPhoto;

    public Long getId() {
        return mId;
    }

    public void setId( Long id ) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName( String nome ) {
        this.mName = nome;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setEndereço( String endereco ) {
        this.mAddress = endereco;
    }

    public String getPhone() {
        return mPhone;
    }

    public void sesetPhone( String telefone ) {
        this.mPhone = telefone;
    }

    public String getWebsite() {
        return mWebsite;
    }

    public void setWebsite( String site ) {
        this.mWebsite = site;
    }

    public Double getGrade() {
        return mGrade;
    }

    public void setGrade( Double nota ) {
        this.mGrade = nota;
    }

    public String getPathPhoto() {
        return mPathPhoto;
    }

    public void setPathPhoto( String caminhoFoto ) {
        this.mPathPhoto = caminhoFoto;
    }

    @Override
    public String toString() {
        return getId() + " - " + getName();
    }
}

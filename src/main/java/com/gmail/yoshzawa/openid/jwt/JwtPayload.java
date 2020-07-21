package com.gmail.yoshzawa.openid.jwt;

public final class JwtPayload {
	private String aud;
	private String iss;
	private String iat;
	private String nbf;
	private String exp;
	private String name;
	private String nonce;
	private String oid;
	private String preferred_username;
	private String sub;
	private String tid;
	private String ver;

	public JwtPayload(String aud, String iss, String iat, String nbf,
			String exp, String name, String nonce, String oid,
			String preferred_username, String sub, String tid, String ver) {
		setAud(aud);
		setIss(iss);
		setIat(iat);
		setNbf(nbf);
		setExp(exp);
		setName(name);
		setNonce(nonce);
		setOid(oid);
		setPreferredUsername(preferred_username);
		setSub(sub);
		setTid(tid);
		setVer(ver);

	}

	public JwtPayload() {
		this(null, null, null, null, null, null, null, null, null, null, null, null);
	}

	public String getAud() {
		return aud;
	}

	public void setAud(String aud) {
		this.aud = aud;
	}

	public String getIss() {
		return iss;
	}

	public void setIss(String iss) {
		this.iss = iss;
	}

	public String getIat() {
		return iat;
	}

	public void setIat(String iat) {
		this.iat = iat;
	}

	public String getNbf() {
		return nbf;
	}

	public void setNbf(String nbf) {
		this.nbf = nbf;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getPreferredUsername() {
		return preferred_username;
	}

	public void setPreferredUsername(String preferredUsername) {
		this.preferred_username = preferredUsername;
	}
	
/*	private Date epochToDate(String s){
		try{
		Long l = Long.parseLong(s);
		l*=1000;
		return new Date(l);
		}catch (NumberFormatException e){
			return null;
		}
	}
*/
}

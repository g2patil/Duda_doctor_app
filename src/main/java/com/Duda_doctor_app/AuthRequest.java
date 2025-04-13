package com.Duda_doctor_app;

public class AuthRequest {
    private String username;
    private String password;
    private long id;
    private long instituteId;
    private long schoolId;

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(long instituteId) {
        this.instituteId = instituteId;
    }

    public long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(long schoolId) {
        this.schoolId = schoolId;
    }

    public static String getHeader(String string) {
        // If needed, implement logic to fetch header from request
        return null;
    }
}


/*
public class AuthRequest {
    private String username;
    private String password;
    private long id;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static String getHeader(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

    // Getters and Setters
}

*/
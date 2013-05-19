package real;

public enum CallBackResponse {
	success("true"),
	failure("false");
	
	private final String value;
	private CallBackResponse(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
	
}

package sax;

public class Lesson {
    /** 课程名称 */
    private String lessonName;
    /** 课程成绩 */
    private int lessonScore;

    public Lesson() {
    }

    public Lesson(String lessonName, int lessonScore) {
        this.lessonName = lessonName;
        this.lessonScore = lessonScore;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public int getLessonScore() {
        return lessonScore;
    }

    public void setLessonScore(int lessonScore) {
        this.lessonScore = lessonScore;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Lesson [lessonName=");
		builder.append(lessonName);
		builder.append(", lessonScore=");
		builder.append(lessonScore);
		builder.append("]");
		return builder.toString();
	}

    
}
package sprint5.peoplepegistration.configuration.exception.errorresponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private String field;
    private String parameter;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    public ErrorResponse(builder builder) {
        this.message = builder.message;
        this.field = builder.field;
        this.parameter = builder.parameter;
        this.timestamp = builder.timestamp;
    }

    @Getter
    @NoArgsConstructor
    public static class builder {
        private String message;
        private String field;
        private String parameter;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime timestamp;

        public builder message(String message) {
            this.message = message;
            return this;
        }

        public builder field(String field) {
            this.field = field;
            return this;
        }

        public builder parameter(String parameter) {
            this.parameter = parameter;
            return this;
        }

        public builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(this);
        }
    }
}



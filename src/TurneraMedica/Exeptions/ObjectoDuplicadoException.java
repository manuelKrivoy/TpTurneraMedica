package TurneraMedica.Exeptions;

    public class ObjectoDuplicadoException extends Exception {
        public ObjectoDuplicadoException() {
        }

        public ObjectoDuplicadoException(String message) {
            super(message);
        }

        public ObjectoDuplicadoException(String message, Throwable cause) {
            super(message, cause);
        }

        public ObjectoDuplicadoException(Throwable cause) {
            super(cause);
        }
    }


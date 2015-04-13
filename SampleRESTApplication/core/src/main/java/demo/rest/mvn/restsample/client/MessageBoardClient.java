package demo.rest.mvn.restsample.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import demo.rest.mvn.restsample.Message;

public class MessageBoardClient {

    public MessageBoardClient() {
    }

    public List<Message> getMessages() {
        MessageBoardResourceBean_JerseyClient client = new MessageBoardResourceBean_JerseyClient();
        List<Message> msgs = client.getMessages_XML(Message.class);
        client.close();
        return msgs;
    }

    public void addMessage(Message m) {
        MessageBoardResourceBean_JerseyClient client = new MessageBoardResourceBean_JerseyClient();
        client.addMessage(m);
        client.close();
    }

    public void deleteMessage(Message m) {
        MessageBoardResourceBean_JerseyClient client = new MessageBoardResourceBean_JerseyClient();
        client.deleteMessage(String.valueOf(m.getUniqueId()));
        client.close();
    }

    public void updateMessage(Message m) {
        MessageBoardResourceBean_JerseyClient client = new MessageBoardResourceBean_JerseyClient();
        client.deleteMessage(String.valueOf(m.getUniqueId()));
        client.addMessage(m);
        client.close();
    }

    private static class MessageBoardResourceBean_JerseyClient {

        private WebResource webResource;
        private Client client;
        private static final String BASE_URI = "http://localhost:8080/message-board/app"; //NOI18N

        MessageBoardResourceBean_JerseyClient() {
            client = new Client();
            webResource = client.resource(BASE_URI).path("messages"); //NOI18N
        }

        public void deleteMessage(String msgNum) throws UniformInterfaceException {
            webResource.path(java.text.MessageFormat.format("{0}", new Object[]{msgNum})).delete();
        }

        public <T> T getMessage_XML(Class<T> responseType, String msgNum) throws UniformInterfaceException {
            return webResource.path(java.text.MessageFormat.format("{0}", new Object[]{msgNum})).accept(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
        }

        public <T> T getMessage_HTML(Class<T> responseType, String msgNum) throws UniformInterfaceException {
            return webResource.path(java.text.MessageFormat.format("{0}", new Object[]{msgNum})).accept(javax.ws.rs.core.MediaType.TEXT_HTML).get(responseType);
        }

        public ClientResponse addMessage(Object requestEntity) throws UniformInterfaceException {
            return webResource.type(javax.ws.rs.core.MediaType.APPLICATION_XML).post(ClientResponse.class, requestEntity);
        }

        public <T> List<T> getMessages_XML(final Class<T> responseType) throws UniformInterfaceException {
            GenericType<List<T>> type = new GenericType<List<T>>(new PType(responseType)) {};
            return webResource.accept(javax.ws.rs.core.MediaType.APPLICATION_XML).get(type);
        }

        public <T> List<T> getMessages_HTML(Class<T> responseType) throws UniformInterfaceException {
            GenericType<List<T>> type = new GenericType<List<T>>(new PType(responseType)) {};
            return webResource.accept(javax.ws.rs.core.MediaType.TEXT_HTML).get(type);
        }

        public void close() {
            client.destroy();
        }
    }

    private static class PType implements ParameterizedType {

        private Class<?> responseType;

        PType(Class<?> responseType) {
            this.responseType = responseType;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{responseType};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return List.class;
        }
    }
}

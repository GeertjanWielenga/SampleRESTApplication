package demo.rest.mvn.restsample.explorer;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.Lookups;
import demo.rest.mvn.restsample.Message;

public class MessagesRootNode extends AbstractNode {

    public MessagesRootNode(Children children) {
        super(children, Lookups.fixed(new Message(null, null, -1)));
        setDisplayName("Messages"); //NOI18N
        setName("Messages"); //NOI18N
        setShortDescription("All Messages"); //NOI18N
    }

}

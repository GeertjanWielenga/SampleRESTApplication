package demo.rest.mvn.restsample.explorer;

import demo.rest.mvn.restsample.Message;
import demo.rest.mvn.restsample.client.MessageBoardClient;
//import demo.rest.mvn.restsample.editor.MsgDetailsTopComponent;
import java.io.IOException;
import java.util.List;
import org.openide.actions.DeleteAction;
import org.openide.actions.OpenAction;
import org.openide.cookies.OpenCookie;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.Lookups;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 *
 * @author lukas
 */
public class MsgChildFactory extends ChildFactory<Message> {

    private List<Message> msgs;

    public MsgChildFactory(List<Message> msgs) {
        this.msgs = msgs;
    }

    @Override
    protected boolean createKeys(List<Message> list) {
        for (Message msg : msgs) {
            list.add(msg);
        }
        return true;
    }

    @Override
    protected Node createNodeForKey(final Message key) {
        Node node = new AbstractNode(Children.LEAF, Lookups.fixed(
                key,
                new OpenCookie() {

                    @Override
                    public void open() {
                        TopComponent win = WindowManager.getDefault().findTopComponent("MsgDetailsTopComponent");
//                        MsgDetailsTopComponent win = MsgDetailsTopComponent.findInstance();
                        win.open();
                    }
                })) {

            @Override
            public SystemAction[] getActions() {
                SystemAction[] saa = new SystemAction[2];
                saa[0] = SystemAction.get(OpenAction.class);
                saa[1] = SystemAction.get(DeleteAction.class);
                return saa;
            }

            @Override
            public boolean canDestroy() {
                return true;
            }

            @Override
            public void destroy() throws IOException {
                MessageBoardClient client = new MessageBoardClient();
                client.deleteMessage(key);
                ExplorerTopComponent.refresh();
            }

        };
        node.setDisplayName(key.getMessage());
        node.setShortDescription(key.getCreated().toString());
        return node;
    }
}

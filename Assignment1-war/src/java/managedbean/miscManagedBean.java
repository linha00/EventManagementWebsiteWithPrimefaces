package managedbean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "miscManagedBean")
@RequestScoped
public class miscManagedBean {
    
    private String[] text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum lorem est, rutrum nec nulla ac, convallis congue diam. Quisque eget magna vitae augue ornare fermentum nec eu nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Donec vitae sollicitudin eros. Suspendisse sit amet lobortis mauris. Duis sollicitudin id tellus id semper. Pellentesque ac nisl et erat feugiat efficitur. Quisque vestibulum accumsan lorem molestie dapibus. Sed cursus, elit quis sollicitudin vulputate, purus metus facilisis neque, elementum ullamcorper eros sem vel sem. In blandit erat eget semper lacinia. Fusce vel dui semper, ornare mauris vitae, scelerisque risus. Ut tincidunt at lacus vitae interdum. Fusce vehicula vestibulum enim, at lacinia lectus finibus at. Ut a venenatis justo. Proin dignissim tincidunt enim, in convallis magna gravida at. Quisque sagittis interdum pulvinar.".split(" ");
    
    public miscManagedBean() {
    }
    
    public String getTempText(int length) {
        String out = "";
        int temp = 0;
        while(length > 0) {
            out += text[temp] + " ";
            length--;
            temp++;
            if (temp == 130) {
                temp = 0;
            }
        }
        return out;
    }
    
}

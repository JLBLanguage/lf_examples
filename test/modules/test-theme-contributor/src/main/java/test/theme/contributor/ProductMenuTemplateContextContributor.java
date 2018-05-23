package test.theme.contributor;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.template.TemplateContextContributor;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Jan Brychta
 */
@Component(
        immediate = true,
        property = {"type=" + TemplateContextContributor.TYPE_THEME},
        service = TemplateContextContributor.class
)
public class ProductMenuTemplateContextContributor implements TemplateContextContributor{

    @Override
    public void prepare(
            Map<String, Object> contextObjects, HttpServletRequest request) {

        if (!isShowControlMenu(request)) {
            return;
        }

        String cssClass = GetterUtil.getString(
                contextObjects.get("bodyCssClass"));

        contextObjects.put("bodyCssClass", cssClass + " has-control-menu");
    }

    protected boolean isShowControlMenu(HttpServletRequest request) {
        ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
                WebKeys.THEME_DISPLAY);

        if (themeDisplay.isImpersonated()) {
            return true;
        }

        if (!themeDisplay.isSignedIn()) {
            return false;
        }

        User user = themeDisplay.getUser();

        if (!user.isSetupComplete()) {
            return false;
        }

        return true;
    }
}

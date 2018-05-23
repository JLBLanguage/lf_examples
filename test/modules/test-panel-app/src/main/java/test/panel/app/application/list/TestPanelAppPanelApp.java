package test.panel.app.application.list;

import test.panel.app.constants.TestPanelAppPanelCategoryKeys;
import test.panel.app.constants.TestPanelAppPortletKeys;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author honza
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=100",
		"panel.category.key=" + TestPanelAppPanelCategoryKeys.CONTROL_PANEL_CATEGORY
	},
	service = PanelApp.class
)
public class TestPanelAppPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return TestPanelAppPortletKeys.TestPanelApp;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + TestPanelAppPortletKeys.TestPanelApp + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

}
package test.service.builder.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import test.service.builder.model.Guestbook;
import test.service.builder.permission.GuestbookPermission;
import test.service.builder.service.GuestbookLocalService;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.servlet.ServletContext;

/**
 * @author Jan Brychta
 */
@Component(immediate = true,
        property = {"javax.portlet.name=" + GuestbookAssetRenderer.GuestBook},
        service = AssetRendererFactory.class
)
public class GuestbookAssetRendererFactory extends
        BaseAssetRendererFactory<Guestbook> {

    public GuestbookAssetRendererFactory() {
        setClassName(CLASS_NAME);
        setLinkable(_LINKABLE);
        setPortletId(GuestbookAssetRenderer.GuestBook);
        setSearchable(true);
        setSelectable(true);
    }

    @Override
    public AssetRenderer<Guestbook> getAssetRenderer(long classPK, int type)
            throws PortalException {

        Guestbook guestbook = _guestbookLocalService.getGuestbook(classPK);

        GuestbookAssetRenderer guestbookAssetRenderer =
                new GuestbookAssetRenderer(guestbook);

        guestbookAssetRenderer.setAssetRendererType(type);
        guestbookAssetRenderer.setServletContext(_servletContext);

        return guestbookAssetRenderer;
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public boolean hasPermission(PermissionChecker permissionChecker,
                                 long classPK, String actionId) throws Exception {

        Guestbook guestbook = _guestbookLocalService.getGuestbook(classPK);
        return GuestbookPermission.contains(permissionChecker, guestbook,
                actionId);
    }

    @Override
    public PortletURL getURLAdd(LiferayPortletRequest liferayPortletRequest,
                                LiferayPortletResponse liferayPortletResponse, long classTypeId) {
        PortletURL portletURL = null;

        try {
            ThemeDisplay themeDisplay = (ThemeDisplay)
                    liferayPortletRequest.getAttribute(WebKeys.THEME_DISPLAY);

            portletURL = liferayPortletResponse.createLiferayPortletURL(getControlPanelPlid(themeDisplay),
                    GuestbookAssetRenderer.GuestBook, PortletRequest.RENDER_PHASE);
            portletURL.setParameter("mvcRenderCommandName", "/guestbookwebportlet/edit_guestbook");
            portletURL.setParameter("showback", Boolean.FALSE.toString());
        } catch (PortalException e) {
        }

        return portletURL;
    }

    @Override
    public boolean isLinkable() {
        return _LINKABLE;
    }

    @Override
    public String getIconCssClass() {
        return "bookmarks";
    }

    @Reference(target = "(osgi.web.symbolicname=guest.book)",
            unbind = "-")
    public void setServletContext(ServletContext servletContext) {
        _servletContext = servletContext;
    }
    private ServletContext _servletContext;

    @Reference(unbind = "-")
    protected void setGuestbookLocalService(GuestbookLocalService guestbookLocalService) {
        _guestbookLocalService = guestbookLocalService;
    }

    private GuestbookLocalService _guestbookLocalService;
    private static final boolean _LINKABLE = true;
    public static final String CLASS_NAME = Guestbook.class.getName();
    public static final String TYPE = "guestbook";
}
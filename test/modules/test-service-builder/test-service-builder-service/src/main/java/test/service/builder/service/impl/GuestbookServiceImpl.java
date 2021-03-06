/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package test.service.builder.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.ServiceContext;
import test.service.builder.model.Guestbook;
import test.service.builder.permission.GuestbookModelPermission;
import test.service.builder.permission.GuestbookPermission;
import test.service.builder.service.base.GuestbookServiceBaseImpl;
import test.service.builder.util.ActionKeys;

import java.util.List;

/**
 * The implementation of the guestbook remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link test.service.builder.service.GuestbookService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GuestbookServiceBaseImpl
 * @see test.service.builder.service.GuestbookServiceUtil
 */
public class GuestbookServiceImpl extends GuestbookServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link test.service.builder.service.GuestbookServiceUtil} to access the guestbook remote service.
	 */

    public Guestbook addGuestbook(long userId, String name,
                                  ServiceContext serviceContext) throws SystemException,
            PortalException {


        GuestbookModelPermission.check(getPermissionChecker(),
                serviceContext.getScopeGroupId(), ActionKeys.ADD_GUESTBOOK);


        return guestbookLocalService.addGuestbook(userId, name, serviceContext);
    }

    public Guestbook deleteGuestbook(long guestbookId,
                                     ServiceContext serviceContext) throws PortalException,
            SystemException {

        GuestbookPermission.check(getPermissionChecker(), guestbookId,
                ActionKeys.DELETE);


        return guestbookLocalService.deleteGuestbook(guestbookId, serviceContext);
    }

    public List<Guestbook> getGuestbooks(long groupId) throws SystemException {
        return guestbookPersistence.filterFindByGroupId(groupId);
    }

    public List<Guestbook> getGuestbooks(long groupId, int start, int end)
            throws SystemException {
        return guestbookPersistence.filterFindByGroupId(groupId, start, end);
    }

    public int getGuestbooksCount(long groupId) throws SystemException {
        return guestbookPersistence.filterCountByGroupId(groupId);
    }

    public Guestbook updateGuestbook(long userId, long guestbookId,
                                     String name, ServiceContext serviceContext) throws PortalException,
            SystemException {

        GuestbookPermission.check(getPermissionChecker(), guestbookId,
                ActionKeys.UPDATE);

        return guestbookLocalService.updateGuestbook(userId, guestbookId,
                name, serviceContext);
    }
}
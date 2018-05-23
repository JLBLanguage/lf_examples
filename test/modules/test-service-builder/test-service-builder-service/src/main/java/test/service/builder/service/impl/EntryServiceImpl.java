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
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import test.service.builder.exception.EntryEmailException;
import test.service.builder.exception.EntryMessageException;
import test.service.builder.exception.EntryNameException;
import test.service.builder.model.Entry;
import test.service.builder.permission.EntryPermission;
import test.service.builder.permission.GuestbookModelPermission;
import test.service.builder.permission.GuestbookPermission;
import test.service.builder.service.base.EntryServiceBaseImpl;
import test.service.builder.util.ActionKeys;

import java.util.Date;
import java.util.List;

import static test.service.builder.service.EntryLocalServiceUtil.getEntry;

/**
 * The implementation of the entry remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link test.service.builder.service.EntryService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntryServiceBaseImpl
 * @see test.service.builder.service.EntryServiceUtil
 */
public class EntryServiceImpl extends EntryServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link test.service.builder.service.EntryServiceUtil} to access the entry remote service.
	 */

    public Entry addEntry(long userId, long guestbookId, String name,
                          String email, String message, ServiceContext serviceContext)
            throws PortalException, SystemException {

//        GuestbookModelPermission.check(getPermissionChecker(),
//                serviceContext.getScopeGroupId(), ActionKeys.ADD_ENTRY);


        return entryLocalService.addEntry(userId, guestbookId, name, email,
                message, serviceContext);
    }

    public Entry deleteEntry(long entryId, ServiceContext serviceContext)
            throws PortalException, SystemException {

        EntryPermission.check(getPermissionChecker(), entryId, ActionKeys.DELETE);

        return entryLocalService.deleteEntry(entryId, serviceContext);
    }

    public List<Entry> getEntries(long groupId, long guestbookId)
            throws SystemException {

        return entryPersistence.filterFindByG_G(groupId, guestbookId);
    }

    public List<Entry> getEntries(long groupId, long guestbookId, int start,
                                  int end) throws SystemException {

        return entryPersistence.filterFindByG_G(groupId, guestbookId, start, end);
    }

    public int getEntriesCount(long groupId, long guestbookId)
            throws SystemException {

        return entryPersistence.countByG_G(groupId, guestbookId);
    }

    public Entry updateEntry(long userId, long guestbookId, long entryId,
                             String name, String email, String message,
                             ServiceContext serviceContext) throws PortalException,
            SystemException {

        EntryPermission.check(getPermissionChecker(), entryId, ActionKeys.UPDATE);


        return entryLocalService.updateEntry(userId, guestbookId, entryId,
                name, email, message, serviceContext);
    }

}
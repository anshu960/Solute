import React, { ReactNode, useRef, useState } from 'react'
import { Link as RouterLink } from 'react-router-dom'
import { set, sub, formatDistanceToNow } from 'date-fns'
//import { Icon } from '@iconify/react'
import NotificationsNoneIcon from '@mui/icons-material/NotificationsNone';
// import bellFill from '@iconify/icons-eva/bell-fill'
// import clockFill from '@iconify/icons-eva/clock-fill'
// import doneAllFill from '@iconify/icons-eva/done-all-fill'
// material
import {
  Box,
  List,
  Badge,
  Button,
  Avatar,
  Tooltip,
  Divider,
  ListItem,
  Typography,
  ListItemText,
  ListSubheader,
  ListItemAvatar
} from '@mui/material'
// components
//import Scrollbar from '../../Components/Scrollbar/Scrollbar'
import MenuPopover from '../../components/MenuPopover';
//import MIconButton from '../../omponents/MaterialExtend/MIconButton'
// Images
import avatars2 from '../../images/Indian_Oil_Logo.png'
// import avatars2 from '../../Common/images/mock-images/avatar_2.jpeg'
// import notificationPackage from '../../Common/icons/ic_notification_package.svg'
// import notificationShipping from '../../Common/icons/ic_notification_shipping.svg'
// import notificationMail from '../../Common/icons/ic_notification_mail.svg'
// import notificationChat from '../../Common/icons/ic_notification_chat.svg'
// ----------------------------------------------------------------------

const NOTIFICATIONS = [
  {
    id: 1,
    title: 'Your order is placed',
    description: 'waiting for shipping',
    avatar: null,
    type: 'order_placed',
    createdAt: set(new Date(), { hours: 10, minutes: 30 }),
    isUnRead: true
  },
  {
    id: 2,
    title: 'test',
    description: 'answered to your comment on the Minimal',
    avatar: avatars2,
    type: 'friend_interactive',
    createdAt: sub(new Date(), { hours: 3, minutes: 30 }),
    isUnRead: true
  },
  {
    id: 3,
    title: 'You have new message',
    description: '5 unread messages',
    avatar: null,
    type: 'chat_message',
    createdAt: sub(new Date(), { days: 1, hours: 3, minutes: 30 }),
    isUnRead: false
  },
  {
    id: 3,
    title: 'You have new mail',
    description: 'sent from Guido Padberg',
    avatar: null,
    type: 'mail',
    createdAt: sub(new Date(), { days: 2, hours: 3, minutes: 30 }),
    isUnRead: false
  },
  {
    id: 4,
    title: 'Delivery processing',
    description: 'Your order is being shipped',
    avatar: null,
    type: 'order_shipped',
    createdAt: sub(new Date(), { days: 3, hours: 3, minutes: 30 }),
    isUnRead: false
  }
]

function renderContent(notification) {
  const title = (
    <Typography variant="subtitle2">
      {notification.title}
      <Typography
        component="span"
        variant="body2"
        sx={{ color: 'text.secondary' }}
      >
        &nbsp; {notification.description}
      </Typography>
    </Typography>
  )

  if (notification.type === 'order_placed') {
    return {
      avatar: <img alt={notification.title} src={avatars2} />,
      title
    }
  }
  if (notification.type === 'order_shipped') {
    return {
      avatar: <img alt={notification.title} src={avatars2} />,
      title
    }
  }
  if (notification.type === 'mail') {
    return {
      avatar: <img alt={notification.title} src={avatars2} />,
      title
    }
  }
  if (notification.type === 'chat_message') {
    return {
      avatar: <img alt={notification.title} src={avatars2} />,
      title
    }
  }
  return {
    avatar: <img alt={notification.title} src={avatars2} />,
    title
  }
}

function NotificationItem({ notification }) {
  // @ts-ignore
  const { avatar, title } = renderContent(notification)
  // @ts-ignore
  const { id, isUnRead } = notification
  return (
    <ListItem
      button
      to="#"
      disableGutters
      key={id}
      component={RouterLink}
      sx={{
        py: 1.5,
        px: 2.5,
        '&:not(:last-of-type)': { mb: '1px' },
        ...(isUnRead && {
          backgroundColor: 'action.selected'
        })
      }}
    >
      <ListItemAvatar>
        <Avatar sx={{ backgroundColor: 'background.neutral' }}>{avatar}</Avatar>
      </ListItemAvatar>
      <ListItemText
        primary={title}
        secondary={
          <Typography
            variant="caption"
            sx={{
              mt: 0.5,
              display: 'flex',
              alignItems: 'center',
              color: 'text.disabled'
            }}
          >
            <Box
              component="img"
              icon={NotificationsNoneIcon}
              sx={{ mr: 0.5, width: 16, height: 16 }}
            />
            {/* @ts-ignore */}
            {formatDistanceToNow(new Date(notification.createdAt))}
          </Typography>
        }
      />
    </ListItem>
  )
}

export default function NotificationsPopover() {
  const anchorRef = useRef(null)
  const [open, setOpen] = useState(false)
  const [notifications, setNotifications] = useState(NOTIFICATIONS)
  const totalUnRead = notifications.filter(item => item.isUnRead).length

  const handleMarkAllAsRead = () => {
    setNotifications(
      notifications.map(notification => ({
        ...notification,
        isUnRead: false
      }))
    )
  }
  return (
    <>
      {/* <MIconButton
        ref={anchorRef}
        onClick={() => setOpen(true)}
        color={open ? 'primary' : 'default'}
      > */}
        <Badge badgeContent={totalUnRead} color="error">
          <NotificationsNoneIcon width={20} height={20}
          ref={anchorRef}
          onClick={() => setOpen(true)}
          color={open ? 'primary' : 'info'}
          />
          {/* <Icon icon={NotificationsNoneIcon} width={20} height={20} /> */}
        </Badge>
      {/* </MIconButton> */}

      <MenuPopover
        open={open}
        onClose={() => setOpen(false)}
        anchorEl={anchorRef.current}
        sx={{ width: 360 }}
      >
        <Box sx={{ display: 'flex', alignItems: 'center', py: 2, px: 2.5 }}>
          <Box sx={{ flexGrow: 1 }}>
            <Typography variant="subtitle1">Notifications</Typography>
            <Typography variant="body2" sx={{ color: 'text.secondary' }}>
              You have {totalUnRead} unread messages
            </Typography>
          </Box>

          {totalUnRead > 0 && (
            <Tooltip title=" Mark all as read">
              {/* <MIconButton color="primary" onClick={handleMarkAllAsRead}> */}
              <NotificationsNoneIcon />
                {/* <Icon icon={NotificationsNoneIcon} width={20} height={20} /> */}
              {/* </MIconButton> */}
            </Tooltip>
          )}
        </Box>

        <Divider />

        <Box sx={{ height: { xs: 340, sm: 'auto' } }}>
          <List
            disablePadding
            subheader={
              <ListSubheader
                disableSticky
                sx={{ py: 1, px: 2.5, typography: 'overline' }}
              >
                New
              </ListSubheader>
            }
          >
            {notifications.slice(0, 2).map(notification => (
              // @ts-ignore
              <NotificationItem
                key={notification.id}
                notification={notification}
              />
            ))}
          </List>

          <List
            disablePadding
            subheader={
              <ListSubheader
                disableSticky
                sx={{ py: 1, px: 2.5, typography: 'overline' }}
              >
                Before that
              </ListSubheader>
            }
          >
            {notifications.slice(2, 5).map(notification => (
              // @ts-ignore
              <NotificationItem
                key={notification.id}
                notification={notification}
              />
            ))}
          </List>
        </Box>

        <Divider />

        <Box sx={{ p: 1 }}>
          <Button fullWidth disableRipple component={RouterLink} to="#">
            View All
          </Button>
        </Box>
      </MenuPopover>
    </>
  )
}

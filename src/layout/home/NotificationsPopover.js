import React, { Fragment, ReactNode, useEffect, useRef, useState } from 'react'
import { Link as RouterLink } from 'react-router-dom'
import { set, sub, formatDistanceToNow } from 'date-fns'
import {useDispatch, useSelector} from 'react-redux'
import NotificationsNoneIcon from '@mui/icons-material/NotificationsNone';
import notification_type from '../../config/notification_type';
import CheckIcon from '@mui/icons-material/Check';
import ClearIcon from '@mui/icons-material/Clear';
import {acceptRequest,rejectRequest} from "./../../helper/notification_helper";
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
import MenuPopover from '../../components/MenuPopover';
import { retriveNotification } from '../../store/notification';

function renderContent(notification) {
  const title = (
    <Box>
    <Typography variant="subtitle2">
      {notification.Title}
    </Typography>
    <Typography
    component="span"
    variant="body2"
    sx={{ color: 'text.secondary' }}
  >
  {notification.Message}
  </Typography>
  </Box>
  )
  return {
    avatar: <img alt={notification.Title} src={notification.Image} />,
    title
  }
}

function NotificationItem({ notification }) {
  // @ts-ignore
  const { avatar, title } = renderContent(notification)
  // @ts-ignore
  const { id, isUnRead } = notification
  return (<Fragment>
    <ListItem
      button
      to="#"
      disableGutters
      key={id}
      component={RouterLink}
      sx={{
        py: 1.5,
        px: 2.5,
        '&:not(:last-of-Type)': { mb: '1px' },
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
      />
    </ListItem>
    <ListItemText
    secondary={
      <Box sx={{display: 'flex', alignItems: 'center', flexDirection: 'column'}}>
        {notification.Type === notification_type.REQUEST ? 
        <Box sx={{display: 'flex', justifyContent: 'space-between', width: '70%'}}>
          <Button
            color="info"
            variant="contained"
            onClick={()=>{acceptRequest(notification)}}
            startIcon={<CheckIcon />}>
              Accept
          </Button>
          <Button
          color="error"
          variant="outlined"
          onClick={()=>{rejectRequest(notification)}}
          startIcon={<ClearIcon />}>
            Reject
          </Button>
        </Box>
       :
        null
        }
        {/* @ts-ignore */}
        {formatDistanceToNow(new Date(notification.CreatedAt))}
      </Box>
    }
  />
  </Fragment>
  )
}

export default function NotificationsPopover() {
  const anchorRef = useRef(null)
  const dispatch = useDispatch()
  const [open, setOpen] = useState(false)
  const notifications = useSelector(state=>state.Notification.allNotification)
  const totalUnRead = notifications.filter(item => !item.IsRead).length

  useEffect(()=>{
    dispatch(retriveNotification())
  },[])
  const retriveNotifications=()=>{
    dispatch(retriveNotification())
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
          onClick={() => {
            setOpen(true) 
            retriveNotifications()
          }}
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
                key={notification._id}
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
                key={notification._id}
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

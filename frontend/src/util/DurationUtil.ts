import {Duration} from "moment";


export function formatDuration(duration: Duration) {
  let str = '';
  if (duration.days() > 1) str = str + Math.floor(duration.days()) + ":"
  if (duration.hours() > 1) str = str + Math.floor(duration.hours()) + ":"
  if (duration.minutes() > 1) str = str + Math.floor(duration.minutes()) + ":"
  if (duration.seconds() > 1) str = str + Math.floor(duration.seconds())
  return str;
}

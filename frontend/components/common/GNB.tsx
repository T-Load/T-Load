import GuestHeader from "./header/GuestHeader";
import UserHeader from "./header/UserHeader";

const GNB = ({ haveCookie }: { haveCookie: boolean }) => {
  return <>{haveCookie ? <UserHeader /> : <GuestHeader />}</>;
};

export default GNB;

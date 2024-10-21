import GuestHeader from "./header/GuestHeader";

const GNB = ({ haveCookie }: { haveCookie: boolean }) => {
  return (
    <>
      <div>{haveCookie ? <div>어우 나 지금 편집중</div> : <GuestHeader />}</div>
    </>
  );
};

export default GNB;
